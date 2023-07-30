package nod.pro.blogging.model.request.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nod.pro.blogging.entity.Blog;
import nod.pro.blogging.model.base.GenericDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentUpdateDTO extends GenericDTO {

    private String comment;

    private Boolean checkedByModerator;
}
