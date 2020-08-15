package com.nancy.m6project.service.impl;

import com.nancy.m6project.model.status.StatusLike;
import com.nancy.m6project.repositories.account.AccountRepositories;
import com.nancy.m6project.repositories.like.StatusLikeRepositories;
import com.nancy.m6project.repositories.status.StatusRepositoties;
import com.nancy.m6project.service.like.StatusLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Service
public class StatusLikeServiceImpl implements StatusLikeService {
    @Autowired
    private StatusLikeRepositories statusLikeRepositories;

    @Autowired
    private StatusRepositoties statusRepositoties;

    @Autowired
    private AccountRepositories accountRepositories;

    @Override
    public List<StatusLike> findAll() {
        return null;
    }

    @Override
    public List<StatusLike> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<StatusLike> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public StatusLike findById(Long id) {
        return statusLikeRepositories.findById(id).get();
    }

    @Override
    public StatusLike save(StatusLike statusLike)  {
        return statusLikeRepositories.save(statusLike);
    }

    @Override
    public StatusLike update(StatusLike model) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        statusLikeRepositories.deleteById(id);
        StatusLike statusLike = statusLikeRepositories.findById(id).orElse(null);
        return statusLike == null;
    }

    @Override
    public boolean isLike(Long account_id, Long status_id) {
        StatusLike statusLike = statusLikeRepositories.findByAccountIdAndStatusId(account_id, status_id);
        if(statusLike != null){
            return true;
        }else {
            return false;
        }
    }
}
