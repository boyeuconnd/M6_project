package com.nancy.m6project.service.account;

import com.nancy.m6project.model.account.Accounts;

public interface AccountService {

    Iterable<Accounts> findAll();

    Accounts findOne(Long id);

    Accounts findUsersByEmail(String email);

    Accounts save(Accounts accounts);
}
