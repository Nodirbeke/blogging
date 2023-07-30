package nod.pro.blogging.model.request.blog;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nod.pro.blogging.model.base.BaseDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlogCreateDTO implements BaseDTO {

    private String title;

    private String body;

    private String theme;

}
