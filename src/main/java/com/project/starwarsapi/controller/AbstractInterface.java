package com.project.starwarsapi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface AbstractInterface<T, ID> {

    ResponseEntity<Page<T>>findAll(Pageable pageable);

    ResponseEntity<T> findById(Long id);

    ResponseEntity<T> create(T model);

    ResponseEntity<T> update(T model);

    ResponseEntity<T> delete(ID id);
}
