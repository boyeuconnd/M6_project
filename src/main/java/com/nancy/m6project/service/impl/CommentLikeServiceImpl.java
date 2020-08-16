package com.nancy.m6project.service.impl;

import com.nancy.m6project.model.comment.CommentLike;
import com.nancy.m6project.repositories.like.CommentLikeRepositories;
import com.nancy.m6project.service.comment.CommentService;
import com.nancy.m6project.service.like.CommentLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentLikeServiceImpl  implements CommentLikeService {

    @Autowired
    CommentLikeRepositories commentLikeRepositories;


    @Override
    public CommentLike save(CommentLike commentLike) {
        return commentLikeRepositories.save(commentLike);
    }

    @Override
    public CommentLike findByAccountIdAndCommentId(Long account_id, Long comment_id) {
        return commentLikeRepositories.findByAccountIdAndCommentId(account_id,comment_id);
    }

    @Override
    public CommentLike deleteByAccount_IdAndComment_Id(Long account_id, Long comment_id) {
        CommentLike commentLike = commentLikeRepositories.findByAccountIdAndCommentId(account_id,comment_id);
        if (commentLike != null){
            commentLikeRepositories.delete(commentLike);
        }
        return null;
    }

}
