package com.nancy.m6project.repositories.status;

import com.nancy.m6project.model.status.Status;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StatusRepositoties extends CrudRepository<Status,Long> {
    Iterable<Status> findStatusByAccount_IdOrderByCreateDateDesc(Long id);
    List<Status> findStatusesByAccountId(Long id);
}
