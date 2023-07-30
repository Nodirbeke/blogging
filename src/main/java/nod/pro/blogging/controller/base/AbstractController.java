package nod.pro.blogging.controller.base;

import lombok.RequiredArgsConstructor;
import nod.pro.blogging.service.base.BaseService;

@RequiredArgsConstructor
public abstract class AbstractController<S extends BaseService> {
    protected final S service;
}
