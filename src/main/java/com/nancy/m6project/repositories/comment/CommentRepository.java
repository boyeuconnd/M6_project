package com.nancy.m6project.repositories.comment;

import com.nancy.m6project.model.comment.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository  extends CrudRepository<Comment, Long> {
}
