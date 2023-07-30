package nod.pro.blogging.service.base;

import nod.pro.blogging.mapper.base.BaseMapper;
import nod.pro.blogging.repository.base.BaseRepository;

/**
 * @param <R> repository
 * @param <M> mapper
 */
public abstract class AbstractService<
        R extends BaseRepository,
        M extends BaseMapper> implements BaseService {
    protected final R repository;
    protected final M mapper;

    protected AbstractService(R repository, M mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
}
