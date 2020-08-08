package com.nancy.m6project.repositories.account;

import com.nancy.m6project.model.account.Accounts;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepositories extends CrudRepository<Accounts,Long> {
    Accounts findUsersByEmail(String email);
}
