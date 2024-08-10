package com.ltm.be.service.base;

import com.ltm.be.payload.response.PageResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IBaseService<D, E, K> {
    D create(E t);
    D update(E t);
    void deleteById(K id);
    boolean existsById(K id);
    D get(K id);
    List<D> getAll();
    PageResponse<?> getPage(int pageNo, int pageSize);
    PageResponse<?> getPageByList(Page<E> list, int pageNo, int pageSize);
}
