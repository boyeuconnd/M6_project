package com.nancy.m6project.controller.api;

import com.nancy.m6project.model.comment.Comment;
import com.nancy.m6project.service.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentApiController {
    @Autowired
    CommentService commentService;

    @PostMapping("api/comment-create")
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment){
        commentService.save(comment);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }
}
