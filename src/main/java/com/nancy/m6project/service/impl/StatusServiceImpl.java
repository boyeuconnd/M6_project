package com.nancy.m6project.service.impl;

import com.nancy.m6project.model.status.Status;
import com.nancy.m6project.repositories.status.StatusRepositoties;
import com.nancy.m6project.service.status.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StatusServiceImpl implements StatusService {

    @Autowired
    StatusRepositoties statusRepositoties;


    @Override
    public Status save(Status status) {
        return statusRepositoties.save(status);
    }

    @Override
    public Iterable<Status> findStatusByAccount_IdOrderByCreateDateDesc(Long id) {
        return statusRepositoties.findStatusByAccount_IdOrderByCreateDateDesc(id);
    }
}
