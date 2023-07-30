package nod.pro.blogging.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import nod.pro.blogging.entity.Blog;
import nod.pro.blogging.model.base.GenericDTO;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class CommentDTO extends GenericDTO {

    private Long blogId;

    private String comment;

    private Long usefullyCount;

    private Long unusefulCount;

    private Boolean checkedByModerator;

    private LocalDateTime createdAt;

}
