package nod.pro.blogging.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nod.pro.blogging.entity.base.Auditable;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Blog extends Auditable {

    private String title;

    @ManyToOne
    @JoinColumn
    private Theme theme;

    private String body;

    private Long view = 0L;

    private Boolean checkedByModerator = false;


    @OneToMany(cascade = CascadeType.ALL)
    private List<Comment> comment;


}