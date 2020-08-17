package com.nancy.m6project.controller.api;

import com.nancy.m6project.model.account.Account;
import com.nancy.m6project.model.comment.Comment;
import com.nancy.m6project.model.comment.CommentLike;
import com.nancy.m6project.model.response.ResultResponse;
import com.nancy.m6project.model.status.Status;
import com.nancy.m6project.model.status.StatusLike;
import com.nancy.m6project.service.account.AccountService;
import com.nancy.m6project.service.comment.CommentService;
import com.nancy.m6project.service.like.CommentLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentLikeApiController {

    @Autowired
    CommentService commentService;

    @Autowired
    AccountService accountService;

    @Autowired
    CommentLikeService commentLikeService;

    @PostMapping("/api/{account_id}/like-comment/{comment_id}")
    private ResultResponse likeComment(@PathVariable Long account_id, @PathVariable Long comment_id) {
        ResultResponse resultResponse = new ResultResponse();
        try {
            CommentLike commentLike = new CommentLike();
            if (commentLikeService.findByAccountIdAndCommentId(account_id,comment_id) == null){
                Comment comment = commentService.findById(comment_id);
                Account account = accountService.findOne(account_id);
                comment.setTotalLikes(comment.getTotalLikes() + 1);
                commentService.save(comment);
                commentLike.setAccount(account);
                commentLike.setComment(comment);
                commentLikeService.save(commentLike);
                resultResponse.setMessage("success");
            }else {
                resultResponse.setMessage("fail");
            }
        } catch (Exception e) {
            resultResponse.setMessage("fail");
        }
        return resultResponse;
    }

    @DeleteMapping("/api/{account_id}/unlike-comment/{comment_id}")
    private ResultResponse unLikeComment(@PathVariable Long account_id, @PathVariable Long comment_id) {
        ResultResponse resultResponse = new ResultResponse();
        try {
            commentLikeService.deleteByAccount_IdAndComment_Id(account_id,comment_id);
            resultResponse.setMessage("success");
            Comment comment = commentService.findById(comment_id);
            comment.setTotalLikes(comment.getTotalLikes() - 1);
            commentService.save(comment);
        } catch (Exception e) {
            resultResponse.setMessage("fail");
        }
        return resultResponse;
    }
}
