package com.nancy.m6project.service.like;

import com.nancy.m6project.model.comment.CommentLike;

import java.util.List;

public interface CommentLikeService {
    boolean isLike(Long account_id, Long status_id);
    CommentLike save(CommentLike commentLike);
    CommentLike findByAccountIdAndCommentId(Long account_id,Long comment_id);
    void deleteByAccount_IdAndComment_Id(Long account_id,Long comment_id);

    Iterable<CommentLike> findAllByComment_Id(Long id);

    void deleteAllByCommentId(Iterable<CommentLike> commentLikes);
}
