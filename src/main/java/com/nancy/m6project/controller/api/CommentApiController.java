package com.nancy.m6project.controller.api;

import com.nancy.m6project.model.account.Account;
import com.nancy.m6project.model.comment.Comment;
import com.nancy.m6project.model.notification.Notification;
import com.nancy.m6project.model.response.CommentResponse;
import com.nancy.m6project.model.response.ResultResponse;
import com.nancy.m6project.model.status.Status;
import com.nancy.m6project.service.account.AccountService;
import com.nancy.m6project.service.comment.CommentService;
import com.nancy.m6project.service.friendRequest.FriendRequestService;
import com.nancy.m6project.service.like.CommentLikeService;
import com.nancy.m6project.service.notification.NotificationService;
import com.nancy.m6project.service.status.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.security.Principal;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
public class CommentApiController {
    @Autowired
    CommentService commentService;
    @Autowired
    StatusService statusService;
    @Autowired
    FriendRequestService friendRequestService;

    @Autowired
    AccountService accountService;

    @Autowired
    CommentLikeService commentLikeService;

    @Autowired
    NotificationService notificationService;

    @PostMapping("api/comment-create/{id}")
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment, @PathVariable Long id, Principal principal) throws SQLIntegrityConstraintViolationException {
        Status status = statusService.findOne(id);
        Account account = comment.getAccount();
        comment.setStatus(status);
        if (friendRequestService.checkRelation(account.getId(), status.getAccount().getId()).equals("friend") || account.getId() == status.getAccount().getId()) {
            commentService.save(comment);
            Notification notification = notificationService.createNotificationByCommentStatus(account.getId(), id);
            notificationService.save(notification);
        }
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    @GetMapping("api/get-comments/{id}")
    public Iterable<Comment> getAllCommentByStatusID(@PathVariable Long id) {
        Iterable<Comment> commentList = commentService.findCommentByStatusIdOrderByCreatedDateAsc(id);
        return commentList;
    }

    @PutMapping("api/comment-edit/{id_account}/{id_status}")
    public ResponseEntity<Comment> editComment(@RequestBody Comment comment, @PathVariable("id_account") Long id_acocunt, @PathVariable("id_status") Long id_status) throws SQLIntegrityConstraintViolationException {
        Account account = accountService.findOne(id_acocunt);
        Status status = statusService.findOne(id_status);
        comment.setStatus(status);
        if (comment.getAccount().getId().equals(account.getId())) {
            comment.setModifyDate(Timestamp.valueOf(LocalDateTime.now()));
            commentService.save(comment);
        }
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @DeleteMapping("api/comment-delete/{id}")
    public ResultResponse deleteComment(@PathVariable Long id) {
        ResultResponse resultResponse = new ResultResponse();
        if(commentService.delete(id)){
            resultResponse.setMessage("success");
            return resultResponse;
        }
        resultResponse.setMessage("fail");
        return resultResponse;
    }

    @PostMapping("api/add-comment/{status_id}")
    public ResultResponse addComment(@RequestBody Comment comment,
                                     @PathVariable Long status_id) {
        ResultResponse response = new ResultResponse();
        Account account = comment.getAccount();
        Status status = statusService.findOne(status_id);
        try {
            comment.setStatus(status);
            status.setTotalComments(status.getTotalComments() + 1);
            statusService.save(status);
            if (commentService.save(comment) != null) {
                Notification notification = notificationService.createNotificationByCommentStatus(account.getId(), status_id);
                notificationService.save(notification);
                response.setMessage("success");
                return response;
            } else {
                response.setMessage("fail");
            }
        } catch (SQLIntegrityConstraintViolationException throwables) {
            response.setMessage("Exception");
        }
        return response;
    }

    @GetMapping("/api/response-comment/{status_id}/{account_id}")
    public java.util.List<CommentResponse> getCommentResponse(@PathVariable("status_id") Long status_id,@PathVariable("account_id") Long account_id ) {
        java.util.List<CommentResponse> newCommentResponseList = new ArrayList<>();
        Set<Comment> commentList = commentService.findCommentByStatusIdOrderByCreatedDateAsc(status_id);
        for (Comment comment : commentList) {
            CommentResponse commentResponse = new CommentResponse();
            boolean isLike = commentLikeService.isLike(account_id, comment.getId());
            commentResponse.setLike(commentLikeService.isLike(account_id, comment.getId()));
            commentResponse.setComment(comment);
            newCommentResponseList.add(commentResponse);
        }
        return newCommentResponseList;
    }

    @GetMapping("/api/{comment_id}/liked")
    public List<Account> getAllAccountLikedComment(@PathVariable Long comment_id) {
        return accountService.getAllAccountLikedThisComment(comment_id);
    }
}
