package com.nancy.m6project.repositories.like;

import com.nancy.m6project.model.status.StatusLike;
import com.nancy.m6project.service.GenericCRUDService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusLikeRepositories extends CrudRepository<StatusLike, Long> {
    StatusLike findByAccountIdAndStatusId(Long account_id, Long status_id);
    List<StatusLike> findAllByAccountId(Long account_id);

    Iterable<StatusLike> findAllByStatus_Id(Long account_id);
}
