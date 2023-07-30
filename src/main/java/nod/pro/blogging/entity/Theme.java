package nod.pro.blogging.entity;

import jakarta.persistence.Entity;
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
public class Theme extends Auditable {

    private String name;

}
