package nod.pro.blogging.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import nod.pro.blogging.model.base.GenericDTO;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlogDTO extends GenericDTO {

    private String title;

    private String theme;

    private String body;

    private Long view;

    private Boolean checkedByModerator;


    @JsonFormat(locale = "hu", shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "CET")
    private LocalDateTime createdAt;


}
