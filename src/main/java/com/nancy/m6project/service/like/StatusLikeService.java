package com.nancy.m6project.service.like;

import com.nancy.m6project.model.status.StatusLike;
import com.nancy.m6project.service.GenericCRUDService;

public interface StatusLikeService extends GenericCRUDService<StatusLike> {
    boolean isLike(Long account_id, Long status_id);

    StatusLike deleteByAccountIdAndStatusId(Long account_id,Long status_id);
}
