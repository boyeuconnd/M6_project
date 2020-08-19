package com.nancy.m6project.repositories.notification;

import com.nancy.m6project.model.notification.Notification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepositories extends CrudRepository<Notification, Long> {
    @Query(value = "select *\n" +
            "from notification where account_receive = ?\n" +
            "order by notification.is_seen = true",nativeQuery = true)
    List<Notification> findAllByAccountReceiveId(Long accountId);
}
