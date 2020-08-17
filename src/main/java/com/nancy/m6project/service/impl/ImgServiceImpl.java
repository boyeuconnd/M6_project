package com.nancy.m6project.service.impl;

import com.nancy.m6project.model.img.Img;
import com.nancy.m6project.repositories.img.ImgRepository;
import com.nancy.m6project.service.img.ImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Service
public class ImgServiceImpl implements ImgService {

    @Autowired
    private ImgRepository imgRepository;

    @Override
    public List<Img> findAll() {
        return null;
    }

    @Override
    public List<Img> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Img> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Img findById(Long id) {
        return imgRepository.findById(id).get();
    }

    @Override
    public Img save(Img model) throws SQLIntegrityConstraintViolationException {
        return imgRepository.save(model);
    }

    @Override
    public Img update(Img model) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
