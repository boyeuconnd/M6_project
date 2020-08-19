package com.nancy.m6project.service.like;

import com.nancy.m6project.model.status.StatusLike;
import com.nancy.m6project.service.GenericCRUDService;

import java.util.List;

public interface StatusLikeService extends GenericCRUDService<StatusLike> {
//    boolean isLike(Long account_id, Long status_id);

    StatusLike deleteByAccountIdAndStatusId(Long account_id,Long status_id);

    List<Long> getAllStatusLikedIdByAccountId(Long id);

    Iterable<StatusLike> findAllByAccount_Id(Long account_id);
}
