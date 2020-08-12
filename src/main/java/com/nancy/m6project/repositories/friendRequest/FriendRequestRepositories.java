package com.nancy.m6project.repositories.friendRequest;

import com.nancy.m6project.model.friendRequest.FriendRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FriendRequestRepositories extends CrudRepository<FriendRequest, Long> {

    FriendRequest findByAccountSendIdAndAccountReceiveIdAndStatus(Long accountSendId, Long accountReceiveId, Integer status);

    Boolean existsByAccountSendIdOrAccountReceiveId(Long accountSent, Long accountReceive);

    Boolean existsFriendRequestByAccountReceive_IdAndAccountSend_Id(Long sentId, Long receiveId);
}
