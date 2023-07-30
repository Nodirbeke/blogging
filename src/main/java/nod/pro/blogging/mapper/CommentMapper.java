package nod.pro.blogging.mapper;

import nod.pro.blogging.entity.Comment;
import nod.pro.blogging.mapper.base.GenericMapper;
import nod.pro.blogging.model.request.comment.CommentCreateDTO;
import nod.pro.blogging.model.request.comment.CommentUpdateDTO;
import nod.pro.blogging.model.response.CommentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CommentMapper extends GenericMapper<Comment, CommentDTO, CommentCreateDTO, CommentUpdateDTO> {

    @Override
    @Mapping(target = "blogId", expression = "java(comment.getBlog().getId())")
    CommentDTO toDto(Comment comment);

    @Override
    @Mapping(target = "blog", ignore = true)
    Comment fromCreateDto(CommentCreateDTO commentCreateDTO);


}
