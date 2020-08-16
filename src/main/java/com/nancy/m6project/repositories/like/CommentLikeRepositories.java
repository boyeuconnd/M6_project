package com.nancy.m6project.repositories.like;

import com.nancy.m6project.model.comment.CommentLike;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentLikeRepositories extends CrudRepository<CommentLike,Long> {
    CommentLike findByAccountIdAndCommentId(Long account_id,Long comment_id);
}
