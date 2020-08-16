package com.nancy.m6project.service.like;

import com.nancy.m6project.model.comment.CommentLike;

public interface CommentLikeService {

    CommentLike save(CommentLike commentLike);
    CommentLike findByAccountIdAndCommentId(Long account_id,Long comment_id);
    CommentLike deleteByAccount_IdAndComment_Id(Long account_id,Long comment_id);
}
