package com.nancy.m6project.controller.api;

import com.nancy.m6project.model.account.Account;
import com.nancy.m6project.model.friendRequest.FriendRequest;
import com.nancy.m6project.service.account.AccountService;
import com.nancy.m6project.service.friendRequest.FriendRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class FriendRequestApiController {

    public static final int PENDING = 0;
    public static final int ACCEPT = 1;
    public static final int IGNORE = 2;

    @Autowired
    private AccountService accountService;

    @Autowired
    private FriendRequestService friendRequestService;

    @GetMapping("/api/{sent_id}/friend_request/{receive_id}")
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

    @PutMapping("/api/friend_request_response/{friend_request_id}")
    public String AcceptFriendRequest(@PathVariable Long friend_request_id) throws SQLIntegrityConstraintViolationException {
        String messege = "";
        FriendRequest friendRequest = friendRequestService.getFriendRequestById(friend_request_id);
        if (friendRequest != null) {
            friendRequest.setStatus(ACCEPT);
            friendRequestService.save(friendRequest);
            messege = "success";
        } else {
            messege = "fail";
        }
        return messege;
    }

    @DeleteMapping("/api/friend_request_response/{friend_request_id}")
    public String DeleteFriendRequest(@PathVariable Long friend_request_id) {
        String message = "";
        FriendRequest friendRequest = friendRequestService.getFriendRequestById(friend_request_id);
        friendRequestService.delete(friend_request_id);
        message = "xóa thành công";
        return message;

    }

    @GetMapping("/api/{account_id}/friends")
    public List<Account> getAllFriendList(@PathVariable Long account_id) {
        List<FriendRequest> friendRequestList = friendRequestService.getAllFriendByAccountId(account_id, ACCEPT, account_id, ACCEPT);
        List<Account> accountList = new ArrayList<>();
        for (FriendRequest friendRequest : friendRequestList) {
            if (friendRequest.getAccountSend().getId() != account_id) {
                accountList.add(friendRequest.getAccountSend());
            } else {
                accountList.add(friendRequest.getAccountReceive());
            }
        }
        return accountList;
    }

    @GetMapping("/api/{account_id}/friend_request_response")
    public List<Account> getListAccountRequestToMe(@PathVariable Long account_id) {
        List<Account> accountList = new ArrayList<>();
        List<FriendRequest> friendRequestList = friendRequestService.getAllFriendRequestAccountReceived(account_id, PENDING);
        for (FriendRequest friendRequest : friendRequestList) {
            accountList.add(friendRequest.getAccountSend());
        }
        return accountList;
    }


}
