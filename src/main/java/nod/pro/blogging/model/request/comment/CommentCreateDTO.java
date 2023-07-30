package nod.pro.blogging.model.request.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nod.pro.blogging.model.base.BaseDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentCreateDTO implements BaseDTO {

    private Long blogId;

    private String comment;

}
