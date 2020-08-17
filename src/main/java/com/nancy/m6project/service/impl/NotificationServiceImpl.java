package com.nancy.m6project.service.impl;

import com.nancy.m6project.model.notification.Notification;
import com.nancy.m6project.repositories.notification.NotificationRepositories;
import com.nancy.m6project.service.notification.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    private NotificationRepositories notificationRepositories;

    @Override
    public List<Notification> getAllByAccountId(Long accountId) {
        return notificationRepositories.findAllByAccountReceiveId(accountId);
    }
}
