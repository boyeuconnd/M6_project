package com.nancy.m6project.repositories.account;

import com.nancy.m6project.model.account.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepositories extends CrudRepository<Account,Long> {
    Account findAccountsByEmail(String email);
    Iterable<Account> findAllByNameContaining(String name);

    Boolean existsAccountByEmail(String email);

    @Query(value = "select *\n" +
            "from account\n" +
            "         left join status_likes on account.id = status_likes.account_id\n" +
            "where status_id = ?\n" +
            "order by status_likes.created_date desc ", nativeQuery = true )
    List<Account> findAllAccountLikedByStatusId(Long id);


}
