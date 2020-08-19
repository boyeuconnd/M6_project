package com.nancy.m6project.service.friendRequest;

import com.nancy.m6project.model.friendRequest.FriendRequest;
import com.nancy.m6project.service.GenericCRUDService;

import java.util.List;

public interface FriendRequestService extends GenericCRUDService<FriendRequest> {

    List<FriendRequest> getAllFriendRequestAccountReceived(Long accountId, Integer status);

    Boolean existsByAccountSendIdOrAccountReceiveId(Long accountSent, Long accountReceive);

    Boolean existsFriendRequestByAccountReceive_IdAndAccountSend_Id(Long sentId, Long receiveId);

    List<FriendRequest> getAllFriendByAccountId(Long accountSendId, Integer status1, Long accountReceiveId, Integer status2);

    FriendRequest getFriendRequestByAccountSendIdAndAccountReceiveId(Long accountSendId, Long accountReceiveId);

    String checkRelation(Long currentId, Long checkId);

    boolean checkHaveFriend(Long accountId);
}
