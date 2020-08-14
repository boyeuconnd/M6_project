package com.nancy.m6project.service.status;

import com.nancy.m6project.model.status.Status;
import com.nancy.m6project.service.GenericCRUDService;
import org.springframework.stereotype.Service;

import java.util.List;


public interface StatusService extends GenericCRUDService<Status> {
    Status save(Status status);
    Status findOne(Long id);
    Iterable<Status> findStatusByAccount_IdOrderByCreateDateDesc(Long id);
    List<Status> getAllStatusByAccountId(Long id);
}
