package com.nancy.m6project.service.account;

import com.nancy.m6project.model.account.Account;

public interface AccountService {
    Account save(Account account);

    Iterable<Account> findAll();

    Account findOne(Long id);

    Account findUsersByEmail(String email);

}
