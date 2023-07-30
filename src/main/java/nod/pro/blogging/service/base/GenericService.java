package nod.pro.blogging.service.base;

import nod.pro.blogging.model.base.GenericDTO;

import java.io.Serializable;
import java.util.List;


/**
 * Generic read service
 *
 * @param <D> -> Dto
 * @param <K> -> class that defines the primary key for your pojo class
 * @param <C> -> Criteria (For Filtering Request)
 */
public interface GenericService<
        D extends GenericDTO,
        C,
        K extends Serializable> extends BaseService {

    List<D> getAll();

    List<D> getAll(C criteria);

    D get(K id);

    Long totalCount(C criteria);
}
