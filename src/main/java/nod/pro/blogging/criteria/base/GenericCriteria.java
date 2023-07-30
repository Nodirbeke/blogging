package nod.pro.blogging.criteria.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nod.pro.blogging.criteria.base.BaseCriteria;

import java.util.Objects;

@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GenericCriteria implements BaseCriteria {
    private Integer size;
    private Integer page;

    public Integer getPage() {
        if (Objects.isNull(page))
            page = 0;
        return page;
    }

    public Integer getSize() {
        if (Objects.isNull(size))
            size = 10;
        return size;
    }
}
