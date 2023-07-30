package nod.pro.blogging.service;

import nod.pro.blogging.entity.Blog;
import nod.pro.blogging.entity.Comment;
import nod.pro.blogging.entity.Theme;
import nod.pro.blogging.mapper.BlogMapper;
import nod.pro.blogging.model.request.blog.BlogCreateDTO;
import nod.pro.blogging.repository.BlogRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;


@ExtendWith(MockitoExtension.class)
public class BlogServiceTest {

    @InjectMocks
    private BlogService blogService;

    @Mock
    private BlogRepository blogRepository;

    @Mock
    private BlogMapper blogMapper;

    @Mock
    private ThemeService themeService;

    @Mock
    private CommentService commentService;


    @Test
    void createBlogTest() {

        BDDMockito.when(blogMapper.fromCreateDto(ArgumentMatchers.any()))
                .thenReturn(new Blog("Football",
                        null,
                        "Foo",
                        0L,
                        false,
                        new ArrayList<>() {
                        }));

        BDDMockito.when(themeService.getByNameOrCreateNew(ArgumentMatchers.any()))
                .thenReturn(new Theme("Football"));

        Blog blog = new Blog(

                "Football",
                new Theme("Football"),
                "Foo",
                0L,
                false,
                new ArrayList<>() {
                });
        blog.setId(1L);
        BDDMockito.when(blogRepository.save(ArgumentMatchers.any()))
                .thenReturn(blog);

        Long blogId = blogService.create(new BlogCreateDTO());
        Assertions.assertEquals(1L, blogId);
    }


}
