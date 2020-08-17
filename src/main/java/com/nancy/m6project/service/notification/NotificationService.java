package com.nancy.m6project.service.notification;

import com.nancy.m6project.model.notification.Notification;
import com.nancy.m6project.service.GenericCRUDService;

import java.util.List;

public interface NotificationService extends GenericCRUDService<Notification> {
    List<Notification> getAllByAccountId(Long accountId);
}
