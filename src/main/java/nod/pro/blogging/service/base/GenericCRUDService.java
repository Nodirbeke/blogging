package nod.pro.blogging.service.base;

import nod.pro.blogging.model.base.BaseDTO;
import nod.pro.blogging.model.base.GenericDTO;

import java.io.Serializable;

/**
 * Generic Creat Read Update Delete service
 *
 * @param <D>  -> Dto
 * @param <CD> -> Create Dto
 * @param <UD> -> Update Dto
 * @param <K>  -> class that defines the primary key for your pojo class
 * @param <C>  -> Criteria (For Filtering Request)
 */
public interface GenericCRUDService<
        D extends GenericDTO,
        CD extends BaseDTO,
        UD extends GenericDTO,
        C,
        K extends Serializable> extends GenericService<D, C, K> {

    K create(CD createDto);

    Void delete(K id);

    D update(UD updateDto);

}
