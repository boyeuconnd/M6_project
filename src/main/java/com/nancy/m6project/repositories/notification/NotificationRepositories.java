package com.nancy.m6project.repositories.notification;

import com.nancy.m6project.model.notification.Notification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepositories extends CrudRepository<Notification, Long> {
}
