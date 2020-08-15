package com.nancy.m6project.service.comment;

import com.nancy.m6project.model.comment.Comment;
import com.nancy.m6project.service.GenericCRUDService;

import java.util.Set;

public interface CommentService extends GenericCRUDService<Comment> {
    Set<Comment> findCommentByStatusIdOrderByCreatedDateAsc(Long id);

}

