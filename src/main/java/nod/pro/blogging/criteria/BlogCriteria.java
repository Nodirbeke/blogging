package nod.pro.blogging.criteria;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nod.pro.blogging.criteria.base.GenericCriteria;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlogCriteria extends GenericCriteria {

    private Boolean checked;


    public BlogCriteria(Integer size, Integer page) {
        super(size, page);
    }
}
