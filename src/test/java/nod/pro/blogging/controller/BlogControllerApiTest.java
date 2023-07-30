package nod.pro.blogging.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import nod.pro.blogging.IntegrationTest;
import nod.pro.blogging.entity.Blog;
import nod.pro.blogging.model.request.blog.BlogCreateDTO;
import nod.pro.blogging.repository.BlogRepository;
import nod.pro.blogging.service.BlogService;
import nod.pro.blogging.service.CommentService;
import nod.pro.blogging.utils.TestingUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@IntegrationTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@ContextConfiguration(classes = { ApplicationConfig.class })
@WebAppConfiguration
public class BlogControllerApiTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BlogRepository blogRepository;


    @BeforeAll
    void setup() {
        Blog blog = TestingUtils.getBlogEntity();
        blogRepository.save(blog);
    }

    @AfterAll
    void tearDown() {
        blogRepository.deleteAll();
    }


    @Test
    void it_should_create_blog() throws Exception {
        BlogCreateDTO createDTO = new BlogCreateDTO();
        createDTO.setTitle("NEW TITLE");
        createDTO.setTheme("NEW THEME");
        createDTO.setBody("NEW BODY");

        mockMvc
                .perform(post("blog/create")
                        .content(objectMapper.writeValueAsString(createDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(view().name("blog/list"))
        ;
    }


}
