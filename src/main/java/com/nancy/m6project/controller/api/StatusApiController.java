package com.nancy.m6project.controller.api;

import com.nancy.m6project.model.account.Account;
import com.nancy.m6project.model.account.HttpResponse;
import com.nancy.m6project.model.friendRequest.FriendRequest;
import com.nancy.m6project.model.response.ResultResponse;
import com.nancy.m6project.model.comment.Comment;
import com.nancy.m6project.model.status.Status;
import com.nancy.m6project.service.account.AccountService;
import com.nancy.m6project.service.comment.CommentService;
import com.nancy.m6project.service.friendRequest.FriendRequestService;
import com.nancy.m6project.service.status.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
        Iterable<Status> list = statusService.findStatusByAccount_IdOrderByCreateDateDesc(id);
        for (Status status : list) {
            status.setComments((Set<Comment>) commentService.findCommentByStatusIdOrderByCreatedDateAsc(status.getId()));
        }
        return list;
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
    public List<Status> getNewFeed(@PathVariable Long current_id) {
        List<Status> newFeedList = new ArrayList<>();
        List<Status> myStatusList = statusService.getAllStatusByAccountId(current_id);
        List<FriendRequest> friendRequestList = friendRequestService.getAllFriendByAccountId(current_id, 1, current_id, 1);

        List<Long> myFriendListId = new ArrayList<>();
        for (FriendRequest friendRequest : friendRequestList) {
            if (friendRequest.getAccountSend().getId() != current_id) {
                myFriendListId.add(friendRequest.getAccountSend().getId());
            } else {
                myFriendListId.add(friendRequest.getAccountReceive().getId());
            }
        }

        for (Long myFriendId : myFriendListId) {
            List<Status> statusList = statusService.getAllStatusByAccountId(myFriendId);
            for (Status status : statusList) {
                newFeedList.add(status);
            }
        }

        for (Status status : myStatusList) {
            newFeedList.add(status);
        }

        return newFeedList;
    }
}
