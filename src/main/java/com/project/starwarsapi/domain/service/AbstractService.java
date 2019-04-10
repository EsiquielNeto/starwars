package com.project.starwarsapi.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractService<T, ID> implements ServiceInterface<T, ID> {

    abstract JpaRepository<T, ID> getRepository();
    abstract Class<T> getEntityClass();

    @Override
    public Page<T> findAll(Pageable pageable) {
        return getRepository().findAll(pageable);
    }

    @Override
    public T findById(ID id) {
        this.existsById(id);
        return getRepository().getOne(id);
    }

    @Override
    public T create(T model) {
        return getRepository().save(model);
    }

    @Override
    public void delete(ID id) {
        this.existsById(id);
        getRepository().deleteById(id);
    }

    @Override
    public void existsById(ID id) {
        if (!getRepository().existsById(id)) {
            throw new IllegalArgumentException();
        }
    }
}
