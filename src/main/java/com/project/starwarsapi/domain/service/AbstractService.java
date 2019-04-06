package com.project.starwarsapi.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractService<T, ID> implements ServiceInterface<T, ID> {

    abstract JpaRepository<T, ID> getRepository();

    abstract Class<T> getEntityClass();

    public Page<T> findAll(Pageable pageable) {
        return getRepository().findAll(pageable);
    }

    public T findById(ID id) {
        this.existsById(id);
        return getRepository().getOne(id);
    }

    public T create(T model) {
        return getRepository().save(model);
    }

    public T update(T model) {
        return getRepository().save(model);
    }

    public void delete(ID id) {
        this.existsById(id);
        getRepository().deleteById(id);
    }

    public void existsById(ID id) {
        if (!getRepository().existsById(id)) {
            throw new IllegalArgumentException();
        }
    }
}
