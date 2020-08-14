package com.nancy.m6project.service.status;

import com.nancy.m6project.model.status.Status;
import com.nancy.m6project.service.GenericCRUDService;
import org.springframework.stereotype.Service;


public interface StatusService extends GenericCRUDService<Status> {
    Status save(Status status);
    Status findOne(Long id);
    Iterable<Status> findStatusByAccount_IdOrderByCreateDateDesc(Long id);
    Iterable<Status> findAllByContentContainingAndAccount_Id(String keyword, Long id);

}
