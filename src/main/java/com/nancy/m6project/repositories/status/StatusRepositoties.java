package com.nancy.m6project.repositories.status;

import com.nancy.m6project.model.status.Status;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusRepositoties extends CrudRepository<Status, Long> {
    List<Status> findStatusByAccount_IdOrderByCreateDateDesc(Long id);

    Iterable<Status> findAllByContentContainingAndAccount_Id(String keyword, Long id);

    List<Status> findStatusesByAccountId(Long id);

    @Query(value = "select *\n" +
            "from statuses\n" +
            "where id in (select distinct statuses.id from statuses left join friend_request\n" +
            "                                                                 on (account_id = account_receive or account_id = account_send)\n" +
            "             where status =1 and (account_send = ?1 or account_receive = ?1)\n" +
            ") order by statuses.create_date desc limit 5 offset ?2", nativeQuery = true)
    List<Status> getNewFeed(Long id,Integer total_record);

    @Query(value = "select *\n" +
            "from statuses\n" +
            "where id in (select distinct statuses.id from statuses left join friend_request\n" +
            "                                                                 on (account_id = account_receive or account_id = account_send)\n" +
            "             where status =1 and (account_send = ?1 or account_receive = ?1)\n" +
            ") order by statuses.create_date desc limit ?2", nativeQuery = true)
    List<Status> updateNewFeed(Long id,Integer total_record);
}
