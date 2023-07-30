package nod.pro.blogging.mapper;

import nod.pro.blogging.entity.Blog;
import nod.pro.blogging.mapper.base.GenericMapper;
import nod.pro.blogging.model.request.blog.BlogCreateDTO;
import nod.pro.blogging.model.request.blog.BlogUpdateDTO;
import nod.pro.blogging.model.response.BlogDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(componentModel = "spring" )
public interface BlogMapper extends GenericMapper<Blog, BlogDTO, BlogCreateDTO, BlogUpdateDTO> {

    @Override
    @Mapping(target = "theme", expression = "java(blog.getTheme().getName())")
    BlogDTO toDto(Blog blog);

    @Override
    @Mapping(target = "theme", ignore = true)
    Blog fromCreateDto(BlogCreateDTO blogCreateDTO);
}
