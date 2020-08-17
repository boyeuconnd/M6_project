package com.nancy.m6project.service.impl;

import com.nancy.m6project.model.notification.Notification;
import com.nancy.m6project.repositories.notification.NotificationRepositories;
import com.nancy.m6project.service.notification.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    private NotificationRepositories notificationRepositories;

    @Override
    public List<Notification> findAll() {
        return null;
    }

    @Override
    public List<Notification> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Notification> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Notification findById(Long id) {
        return notificationRepositories.findById(id).orElse(null);
    }

    @Override
    public Notification save(Notification model) throws SQLIntegrityConstraintViolationException {
        return notificationRepositories.save(model);
    }

    @Override
    public Notification update(Notification model) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public List<Notification> getAllByAccountId(Long accountId) {
        return notificationRepositories.findAllByAccountReceiveId(accountId);
    }
}
