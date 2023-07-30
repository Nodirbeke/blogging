package nod.pro.blogging.utils;

import nod.pro.blogging.entity.Blog;
import nod.pro.blogging.entity.Theme;
import nod.pro.blogging.model.response.BlogDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class TestingUtils {

    public static Blog getBlogEntity() {
        Blog blog = new Blog(
                "Football",
                new Theme("Football"),
                "Foo",
                0L,
                false,
                new ArrayList<>() {
                });
        blog.setId(1L);
        return blog;
    }

    public static BlogDTO getBlogDTO() {
        BlogDTO organizationDTO = new BlogDTO("Football", "Football", "Foo", 0L,
                false, LocalDateTime.now());
        organizationDTO.setId(1L);
        return organizationDTO;
    }

}
