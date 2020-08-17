package com.nancy.m6project.controller.api;

import com.nancy.m6project.model.account.Account;
import com.nancy.m6project.model.account.HttpResponse;
import com.nancy.m6project.model.friendRequest.FriendRequest;
import com.nancy.m6project.model.response.RelationResponse;
import com.nancy.m6project.service.account.AccountService;
import com.nancy.m6project.service.friendRequest.FriendRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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
    public HttpResponse SendFriendRequest(@PathVariable Long sent_id, @PathVariable Long receive_id) throws SQLIntegrityConstraintViolationException {
        HttpResponse httpResponse = new HttpResponse();
        if (friendRequestService.existsFriendRequestByAccountReceive_IdAndAccountSend_Id(sent_id, receive_id)) {
            httpResponse.setMessage("duplicate");
            return httpResponse;
        }
        FriendRequest newFriendRes = new FriendRequest();
        newFriendRes.setAccountSend(accountService.findOne(sent_id));
        newFriendRes.setAccountReceive(accountService.findOne(receive_id));
        newFriendRes.setStatus(PENDING);
        FriendRequest responseFR = friendRequestService.save(newFriendRes);

        if (responseFR != null) {
            httpResponse.setMessage("success");
            return httpResponse;
        }
        httpResponse.setMessage("fail");
        return httpResponse;
    }

    @PutMapping("/api/friend_request_response/{friend_request_id}")
    public HttpResponse AcceptFriendRequest(@PathVariable Long friend_request_id){
        HttpResponse httpResponse = new HttpResponse();
        try{
            FriendRequest friendRequest = friendRequestService.findById(friend_request_id);
            friendRequest.setStatus(ACCEPT);
            friendRequest.setModifiedDate(Timestamp.valueOf(LocalDateTime.now()));
            friendRequestService.save(friendRequest);
            httpResponse.setMessage("success");
        }catch (Exception e){
            httpResponse.setMessage("fail");
        }
        return httpResponse;
    }

    @DeleteMapping("/api/friend_request_response/{friend_request_id}")
    public HttpResponse DeleteFriendRequest(@PathVariable Long friend_request_id) {
        HttpResponse httpResponse = new HttpResponse();
        friendRequestService.delete(friend_request_id);
        httpResponse.setMessage("xóa thành công");
        return httpResponse;

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
    public List<FriendRequest> getListAccountRequestToMe(@PathVariable Long account_id) {
        List<Account> accountList = new ArrayList<>();
        List<FriendRequest> friendRequestList = friendRequestService.getAllFriendRequestAccountReceived(account_id, PENDING);
        for (FriendRequest friendRequest : friendRequestList) {
            accountList.add(friendRequest.getAccountSend());
        }
        return friendRequestList;
    }

    @GetMapping("/api/{current_id}/check_relation/{check_id}")
    public RelationResponse checkRelationship(@PathVariable Long current_id, @PathVariable Long check_id) {
        RelationResponse relationResponse = new RelationResponse();
        relationResponse.setName(friendRequestService.checkRelation(current_id, check_id));
        return relationResponse;
    }
}
