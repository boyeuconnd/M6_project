package com.nancy.m6project.controller.api;

import com.nancy.m6project.model.account.Account;
import com.nancy.m6project.model.response.ResultResponse;
import com.nancy.m6project.model.status.Status;
import com.nancy.m6project.model.status.StatusLike;
import com.nancy.m6project.service.account.AccountService;
import com.nancy.m6project.service.like.StatusLikeService;
import com.nancy.m6project.service.status.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;

@RestController
public class StatusLikeApiController {
    @Autowired
    private StatusLikeService statusLikeService;

    @Autowired
    private StatusService statusService;

    @Autowired
    private AccountService accountService;

    @PostMapping("/api/{account_id}/like/{status_id}")
    private ResultResponse likeStatus(@PathVariable Long account_id, @PathVariable Long status_id) {
        ResultResponse resultResponse = new ResultResponse();
        try {
            StatusLike statusLike = new StatusLike();
            Status status = statusService.findOne(status_id);
            Account account = accountService.findOne(account_id);
            status.setTotalLikes(status.getTotalLikes() + 1);
            statusService.save(status);
            statusLike.setAccount(account);
            statusLike.setStatus(status);
            statusLikeService.save(statusLike);
            resultResponse.setMessage("success");

        } catch (Exception e) {
            resultResponse.setMessage("fail");
        }
        return resultResponse;
    }

    @DeleteMapping("/api/{statusLike_id}/unlike")
    private ResultResponse unlikeStatus(@PathVariable Long statusLike_id) {
        ResultResponse resultResponse = new ResultResponse();
        try {
            StatusLike statusLike = statusLikeService.findById(statusLike_id);
            Status status = statusLike.getStatus();
            status.setTotalLikes(status.getTotalLikes() - 1);
            statusService.save(status);
            statusLikeService.delete(statusLike_id);
            resultResponse.setMessage("success");

        } catch (Exception e) {
            resultResponse.setMessage("fail");
        }
        return resultResponse;
    }

}
