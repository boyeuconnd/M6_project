package com.nancy.m6project.repositories.comment;

import com.nancy.m6project.model.comment.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface CommentRepository  extends CrudRepository<Comment, Long> {
    Set<Comment> findCommentByStatusIdOrderByCreatedDateAsc(Long id);
}
