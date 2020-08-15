package com.nancy.m6project.service.account;

import com.nancy.m6project.model.account.Account;

import java.util.List;

public interface AccountService {
    Account save(Account account);

    Iterable<Account> findAll();

    Account findOne(Long id);

    Account findAccountsByEmail(String email);

    Iterable<Account> findAllByNameContaining(String name);

    Account findAccountByEmail(String email);

    Boolean existsAccountByEmail(String email);

    List<Account> getAllAccountLikedThisStatus(Long id);



}
