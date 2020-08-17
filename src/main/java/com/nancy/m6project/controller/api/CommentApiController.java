package com.nancy.m6project.controller.api;

import com.nancy.m6project.model.account.Account;
import com.nancy.m6project.model.comment.Comment;
import com.nancy.m6project.model.response.ResultResponse;
import com.nancy.m6project.model.status.Status;
import com.nancy.m6project.service.account.AccountService;
import com.nancy.m6project.service.comment.CommentService;
import com.nancy.m6project.service.friendRequest.FriendRequestService;
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

    @PostMapping("api/comment-create/{id}")
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment, @PathVariable Long id, Principal principal) throws SQLIntegrityConstraintViolationException {
        Status status = statusService.findOne(id);
        Account account = comment.getAccount();
        comment.setStatus(status);
        if (friendRequestService.checkRelation(account.getId(), status.getAccount().getId()).equals("friend") || account.getId() == status.getAccount().getId()) {
            commentService.save(comment);
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
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("api/add-comment/{status_id}")
    public ResultResponse addComment(@RequestBody Comment comment,
                                     @PathVariable Long status_id) {
        ResultResponse response = new ResultResponse();
        try {
            comment.setStatus(statusService.findOne(status_id));
            if (commentService.save(comment) != null) {
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
}
