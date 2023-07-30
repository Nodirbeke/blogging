package nod.pro.blogging.criteria;

import lombok.Getter;
import lombok.Setter;
import nod.pro.blogging.criteria.base.GenericCriteria;
import nod.pro.blogging.entity.Blog;

@Getter
@Setter
public class CommentCriteria extends GenericCriteria {

    public CommentCriteria(Integer size, Integer page) {
        super(size, page);
    }

    public CommentCriteria() {
    }
}
