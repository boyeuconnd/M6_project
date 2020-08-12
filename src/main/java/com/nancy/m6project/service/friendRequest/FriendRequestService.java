package com.nancy.m6project.service.friendRequest;

import com.nancy.m6project.model.friendRequest.FriendRequest;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public interface FriendRequestService {
    boolean isFriend(Long accountSendId1, Long accountReceiveId2, Integer status);

    FriendRequest save(FriendRequest model) throws SQLIntegrityConstraintViolationException;

    Boolean existsByAccountSendIdOrAccountReceiveId(Long accountSent,Long accountReceive);

    Boolean existsFriendRequestByAccountReceive_IdAndAccountSend_Id(Long sentId,Long receiveId);
}
