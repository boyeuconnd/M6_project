package com.nancy.m6project.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public interface GenericCRUDService<T> {
    List<T> findAll();

    List<T> findAll(Sort sort);

    Page<T> findAll(Pageable pageable);

    T findById(Long id);

    T save(T model) throws SQLIntegrityConstraintViolationException;

    T update(T model);

    boolean delete(Long id);
}
