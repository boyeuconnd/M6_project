package com.nancy.m6project.service.comment;

import com.nancy.m6project.model.comment.Comment;

import java.util.Set;

public interface CommentService {
    Comment save(Comment comment);
    Set<Comment> findCommentByStatusIdOrderByCreateDateAsc(Long id);

}
