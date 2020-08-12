package com.nancy.m6project.repositories.friendRequest;

import com.nancy.m6project.model.friendRequest.FriendRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FriendRequestRepositories extends CrudRepository<FriendRequest, Long> {

    List<FriendRequest> findAllByAccountReceiveIdAndStatus(Long accountId, Integer status);

    FriendRequest findFriendRequestById(Long friendRequestId);

    FriendRequest findByAccountSendIdAndAccountReceiveIdAndStatus(Long accountSendId, Long accountReceiveId, Integer status);

    Boolean existsByAccountSendIdOrAccountReceiveId(Long accountSent, Long accountReceive);

    Boolean existsFriendRequestByAccountReceive_IdAndAccountSend_Id(Long sentId, Long receiveId);

    List<FriendRequest> findAllByAccountSendIdAndStatusOrAccountReceiveIdAndStatus(Long accountSendId, Integer status1, Long accountReceiveId, Integer status2);
}
