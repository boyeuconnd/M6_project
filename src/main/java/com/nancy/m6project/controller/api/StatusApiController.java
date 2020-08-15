package com.nancy.m6project.controller.api;

import com.nancy.m6project.model.account.Account;
import com.nancy.m6project.model.account.HttpResponse;
import com.nancy.m6project.model.friendRequest.FriendRequest;
import com.nancy.m6project.model.response.NewFeedResponse;
import com.nancy.m6project.model.response.ResultResponse;
import com.nancy.m6project.model.comment.Comment;
import com.nancy.m6project.model.status.Status;
import com.nancy.m6project.service.account.AccountService;
import com.nancy.m6project.service.comment.CommentService;
import com.nancy.m6project.service.friendRequest.FriendRequestService;
import com.nancy.m6project.service.like.StatusLikeService;
import com.nancy.m6project.service.status.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.NewThreadAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@RestController
public class StatusApiController {

    @Autowired
    AccountService accountService;

    @Autowired
    StatusService statusService;

    @Autowired
    CommentService commentService;

    @Autowired
    FriendRequestService friendRequestService;

    @Autowired
    StatusLikeService statusLikeService;

    @PostMapping("api/{id}/create-status")
    public HttpResponse create(@RequestBody @Validated Status status, @PathVariable Long id) {
        HttpResponse response = new HttpResponse();
        status.setAccount(this.accountService.findOne(id));
        if (statusService.save(status) != null) {
            response.setMessage("success");
        } else {
            response.setMessage("fail");
        }
        return response;
    }

    @GetMapping("api/statuses/{id}")
    public Iterable<Status> listStatus(@PathVariable Long id) {
        return statusService.findStatusByAccount_IdOrderByCreateDateDesc(id);

    }

    @DeleteMapping("/api/statuses/{id}/delete")
    public ResultResponse deleteStatus(@PathVariable Long id) {
        ResultResponse resultResponse = new ResultResponse();
        try {
            Status status = statusService.findById(id);
            statusService.delete(id);
            resultResponse.setMessage("xóa thành công");
        } catch (Exception e) {
            resultResponse.setMessage("fail");
        }

        return resultResponse;
    }

    @GetMapping("/api/newfeed/{current_id}")
    public List<Status> getNewFeedSimple(@PathVariable Long current_id) {
        List<Status> newFeedList = statusService.getNewFeed(current_id);
        return newFeedList;
    }

    @PatchMapping("api/find-status/{id}")
    public Iterable<Status> findStatus(@RequestBody String keyword, @PathVariable Long id) {
        Iterable<Status> listResult = statusService.findAllByContentContainingAndAccount_Id(keyword, id);
        return listResult;
    }

    @GetMapping("/api/{status_id}/liked")
    public List<Account> getAllAccountLikedStatus(@PathVariable Long status_id) {
        List<Account> accountList = accountService.getAllAccountLikedThisStatus(status_id);
        return accountList;
    }

    @GetMapping("/api/get-one-status/{id}")
    public Status getOneStatus(@PathVariable Long id) {
        return statusService.findOne(id);
    }

    @GetMapping("/api/newfeed2/{current_id}")
    public List<NewFeedResponse> getNewFeedResponse(@PathVariable Long current_id) {
        List<NewFeedResponse> newFeedResponseList = new ArrayList<>();
        List<Status> statusList;
        if (friendRequestService.checkHaveFriend(current_id)) {
            statusList = statusService.getNewFeed(current_id);
        } else {
            statusList = statusService.getAllStatusByAccountId(current_id);
        }
        for (Status status : statusList) {
            NewFeedResponse newFeedResponse = new NewFeedResponse();
            newFeedResponse.setLike(statusLikeService.isLike(current_id, status.getId()));
            newFeedResponse.setStatus(status);
            newFeedResponseList.add(newFeedResponse);
        }
        return newFeedResponseList;
    }
}
