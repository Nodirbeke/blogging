package nod.pro.blogging.mapper.base;

import nod.pro.blogging.entity.base.BaseEntity;
import nod.pro.blogging.model.base.BaseDTO;
import nod.pro.blogging.model.base.GenericDTO;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * @param <E>  -> Entity
 * @param <D>  -> Dto
 * @param <CD> -> Create Dto
 * @param <UD> -> Update Dto
 */

public interface GenericMapper<E extends BaseEntity,
        D extends GenericDTO,
        CD extends BaseDTO,
        UD extends GenericDTO> extends BaseMapper {

    D toDto(E e);

    List<D> toDto(List<E> e);

    E fromCreateDto(CD cd);

    E fromUpdateDto(UD ud, @MappingTarget E entity);

}
