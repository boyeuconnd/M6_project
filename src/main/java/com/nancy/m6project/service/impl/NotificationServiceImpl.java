package com.nancy.m6project.service.impl;

import com.nancy.m6project.model.account.Account;
import com.nancy.m6project.model.comment.CommentLike;
import com.nancy.m6project.model.notification.Notification;
import com.nancy.m6project.model.status.Status;
import com.nancy.m6project.model.status.StatusLike;
import com.nancy.m6project.repositories.account.AccountRepositories;
import com.nancy.m6project.repositories.like.CommentLikeRepositories;
import com.nancy.m6project.repositories.like.StatusLikeRepositories;
import com.nancy.m6project.repositories.notification.NotificationRepositories;
import com.nancy.m6project.repositories.status.StatusRepositoties;
import com.nancy.m6project.service.notification.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    private NotificationRepositories notificationRepositories;

    @Autowired
    private AccountRepositories accountRepositories;

    @Autowired
    private StatusLikeRepositories statusLikeRepositories;

    @Autowired
    private CommentLikeRepositories commentLikeRepositories;

    @Autowired
    private StatusRepositoties statusRepositoties;

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

    @Override
    public Notification createNotificationByStatusLike(Long accountSendId, Long statusId) {
        StatusLike statusLike = statusLikeRepositories.findByAccountIdAndStatusId(accountSendId, statusId);
        Account accountSend = accountRepositories.findById(accountSendId).get();
        Account accountReceive = statusLike.getStatus().getAccount();
        Notification notification = new Notification();
        notification.setAccountSend(accountSend);
        notification.setAccountReceive(accountReceive);
        notification.setType(accountSend.getName() + " đã thích một trạng thái của bạn");
        return notification;
    }

    @Override
    public Notification createNotificationByCommentLike(Long accountSendId, Long commentId) {
        CommentLike commentLike = commentLikeRepositories.findByAccountIdAndCommentId(accountSendId, commentId);
        Account accountSend = accountRepositories.findById(accountSendId).get();
        Account accountReceive = commentLike.getComment().getAccount();
        Notification notification = new Notification();
        notification.setAccountSend(accountSend);
        notification.setAccountReceive(accountReceive);
        notification.setType(accountSend.getName() + " đã thích một bình luận của bạn");
        return notification;
    }

    @Override
    public Notification createNotificationByCommentStatus(Long accountSendId, Long statusId) {
        Status status = statusRepositoties.findById(statusId).get();
        Account accountSend = accountRepositories.findById(accountSendId).get();
        Account accountReceive = status.getAccount();
        Notification notification = new Notification();
        notification.setAccountSend(accountSend);
        notification.setAccountReceive(accountReceive);
        notification.setType(accountSend.getName()+" đã bình luận trên 1 trạng thái của bạn");
        return notification;
    }


}
