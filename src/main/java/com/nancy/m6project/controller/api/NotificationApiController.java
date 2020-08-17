package com.nancy.m6project.controller.api;

import com.nancy.m6project.model.notification.Notification;
import com.nancy.m6project.model.response.ResultResponse;
import com.nancy.m6project.service.notification.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@RestController
public class NotificationApiController {
    @Autowired
    private NotificationService notificationService;

    @GetMapping("/api/{account_id}/notification")
    private List<Notification> getAllNotification(@PathVariable Long account_id) {
        List<Notification> notificationList = notificationService.getAllByAccountId(account_id);
        return notificationList;
    }

    @PutMapping("/api/notification/{notification_id}")
    private ResultResponse readNotification(@PathVariable Long notification_id) throws SQLIntegrityConstraintViolationException {
        ResultResponse resultResponse = new ResultResponse();
        Notification notification = notificationService.findById(notification_id);

        if (notification == null) {
            resultResponse.setMessage("thông báo  k có");
        } else {
            notification.setSeen(true);
            notificationService.save(notification);
            resultResponse.setMessage("success");
        }
        return resultResponse;
    }
}
