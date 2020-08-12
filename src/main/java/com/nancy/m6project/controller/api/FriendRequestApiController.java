package com.nancy.m6project.controller.api;

import com.nancy.m6project.model.friendRequest.FriendRequest;
import com.nancy.m6project.service.account.AccountService;
import com.nancy.m6project.service.friendRequest.FriendRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

@RestController
public class FriendRequestApiController {

    public static final int PENDING = 0;
    public static final int ACCEPT = 1;
    public static final int IGNORE = 2;

    @Autowired
    private AccountService accountService;

    @Autowired
    private FriendRequestService friendRequestService;

    @PostMapping("/api/{sent_id}/friend_request/{receive_id}")
    public String SendFriendRequest(@PathVariable Long sent_id, @PathVariable Long receive_id) throws SQLIntegrityConstraintViolationException {
        String message = "";
        if (friendRequestService.existsFriendRequestByAccountReceive_IdAndAccountSend_Id(sent_id, receive_id)) {
            message = "duplicate";
            return message;
        }
        FriendRequest newFriendRes = new FriendRequest();
        newFriendRes.setAccountSend(accountService.findOne(sent_id));
        newFriendRes.setAccountReceive(accountService.findOne(receive_id));
        newFriendRes.setStatus(PENDING);
        FriendRequest responseFR = friendRequestService.save(newFriendRes);

        if (responseFR != null) {
            message = "success";
            return message;
        }
        message = "fail";
        return message;
    }
}
