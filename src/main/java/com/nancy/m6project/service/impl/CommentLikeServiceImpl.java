package com.nancy.m6project.service.impl;

import com.nancy.m6project.model.comment.CommentLike;
import com.nancy.m6project.repositories.like.CommentLikeRepositories;
import com.nancy.m6project.service.comment.CommentService;
import com.nancy.m6project.service.like.CommentLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentLikeServiceImpl  implements CommentLikeService {

    @Autowired
    CommentLikeRepositories commentLikeRepositories;


    @Override
    public boolean isLike(Long account_id, Long comment_id) {
        CommentLike commentLike = commentLikeRepositories.findByAccountIdAndCommentId(account_id,comment_id);
        if (commentLike != null){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public CommentLike save(CommentLike commentLike) {
        return commentLikeRepositories.save(commentLike);
    }

    @Override
    public CommentLike findByAccountIdAndCommentId(Long account_id, Long comment_id) {
        return commentLikeRepositories.findByAccountIdAndCommentId(account_id,comment_id);
    }

    @Override
    public void deleteByAccount_IdAndComment_Id(Long account_id, Long comment_id) {
        CommentLike commentLike = commentLikeRepositories.findByAccountIdAndCommentId(account_id,comment_id);
        if (commentLike != null){
            commentLikeRepositories.delete(commentLike);
        }
    }

    @Override
    public Iterable<CommentLike> findAllByComment_Id(Long id) {
        return commentLikeRepositories.findAllByComment_Id(id);
    }

    @Override
    public void deleteAllByCommentId(Iterable<CommentLike> commentLikes){
        commentLikeRepositories.deleteAll(commentLikes);
    }


}
