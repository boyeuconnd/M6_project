package com.nancy.m6project.controller.api;

import com.nancy.m6project.model.comment.Comment;
import com.nancy.m6project.model.status.Status;
import com.nancy.m6project.service.comment.CommentService;
import com.nancy.m6project.service.status.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class CommentApiController {
    @Autowired
    CommentService commentService;
    @Autowired
    StatusService statusService;

    @PostMapping("api/comment-create/{id}")
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment, @PathVariable Long id, Principal principal){
        Status status = statusService.findOne(id);
        comment.setStatus(status);
        commentService.save(comment);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }
    @GetMapping("api/get-comments/{id}")
    public Iterable<Comment> getAllCommentByStatusID(@PathVariable Long id){
        Iterable<Comment> commentList = commentService.findCommentByStatusIdOrderByCreatedDateAsc(id);
        return commentList;
    }
}
