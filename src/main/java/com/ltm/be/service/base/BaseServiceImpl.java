package com.ltm.be.service.base;

import com.ltm.be.converter.AbstractBaseConverter;
import com.ltm.be.dto.AbstractDto;
import com.ltm.be.entity.AbstractEntity;
import com.ltm.be.exception.ResourceNotFoundException;
import com.ltm.be.payload.response.PageResponse;
import com.ltm.be.repository.BaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

/*
 * @param: D stands for DTO
 * @param: E stands for Entity
 * */

@RequiredArgsConstructor
public class BaseServiceImpl<D extends AbstractDto<K>, E extends AbstractEntity<K>, K> implements IBaseService<D, E, K> {
    private final BaseRepository<E, K> baseRepository;
    private final AbstractBaseConverter<D, E> baseConverter;


    @Override
    public D create(E e) {
        return baseConverter.toDto(baseRepository.save(e));
    }

    @Override
    public D update(E e) {
        return baseConverter.toDto(baseRepository.save(e));
    }

    @Override
    public void deleteById(K id) {
        baseRepository.deleteById(id);
    }

    @Override
    public boolean existsById(K id) {
        return baseRepository.existsById(id);
    }

    @Override
    public D get(K id) {
        return baseConverter.toDto(baseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Object.class + " with id " + id + " not found")));
    }

    @Override
    public List<D> getAll() {
        return baseRepository.findAll().stream().map(baseConverter::toDto).toList();
    }

    @Override
    public PageResponse<?> getPage(int pageNo, int pageSize) {
        int page = pageNo > 0 ? pageNo -1 : pageNo;
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<E> bases = baseRepository.findAll(pageable);

        return PageResponse.builder()
                .page(pageNo)
                .size(pageSize)
                .totalPages(bases.getTotalPages())
                .totalElements(bases.getTotalElements())
                .items(bases.stream().map(baseConverter::toDto).toList())
                .build();
    }

    @Override
    public PageResponse<?> getPageByList(Page<E> list, int pageNo, int pageSize) {
        return PageResponse.builder()
                .page(pageNo)
                .size(pageSize)
                .totalPages(list.getTotalPages())
                .totalElements(list.getTotalElements())
                .items(list.stream().map(baseConverter::toDto).toList())
                .build();
    }
}
