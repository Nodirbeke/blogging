package nod.pro.blogging.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nod.pro.blogging.entity.base.Auditable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comment extends Auditable {

    @ManyToOne
    @JoinColumn
    private Blog blog;

    private String comment;

    private Long usefullyCount = 0L;

    private Long unusefulCount = 0L;

    private Boolean checkedByModerator = false;

}
