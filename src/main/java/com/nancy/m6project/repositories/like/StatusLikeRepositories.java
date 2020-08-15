package com.nancy.m6project.repositories.like;

import com.nancy.m6project.model.status.StatusLike;
import com.nancy.m6project.service.GenericCRUDService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusLikeRepositories extends CrudRepository<StatusLike, Long> {

}
