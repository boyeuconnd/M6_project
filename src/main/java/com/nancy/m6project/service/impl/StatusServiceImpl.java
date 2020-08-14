package com.nancy.m6project.service.impl;

import com.nancy.m6project.model.friendRequest.FriendRequest;
import com.nancy.m6project.model.status.Status;
import com.nancy.m6project.repositories.status.StatusRepositoties;
import com.nancy.m6project.service.status.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StatusServiceImpl implements StatusService {

    @Autowired
    StatusRepositoties statusRepositoties;


    @Override
    public List<Status> findAll() {
        return null;
    }

    @Override
    public List<Status> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Status> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Status findById(Long id) {
        return statusRepositoties.findById(id).get();
    }

    @Override
    public Status save(Status status) {
        return statusRepositoties.save(status);
    }

    @Override
    public Status update(Status model) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        statusRepositoties.deleteById(id);
        Status status = statusRepositoties.findById(id).orElse(null);
        return status == null;
    }

    @Override
    public Iterable<Status> findStatusByAccount_IdOrderByCreateDateDesc(Long id) {
        return statusRepositoties.findStatusByAccount_IdOrderByCreateDateDesc(id);
    }
}
