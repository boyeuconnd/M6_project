package com.nancy.m6project.service.notification;

import com.nancy.m6project.model.notification.Notification;

import java.util.List;

public interface NotificationService {
    List<Notification> getAllByAccountId(Long accountId);
}
