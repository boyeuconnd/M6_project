package com.nancy.m6project.service.impl;

import com.nancy.m6project.model.account.Account;
import com.nancy.m6project.repositories.account.AccountRepositories;
import com.nancy.m6project.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService, UserDetailsService {

    @Autowired
    private AccountRepositories accountRepositories;

    @Override
    public Account save(Account account) {
        return accountRepositories.save(account);
    }

    @Override
    public Iterable<Account> findAll() {
        return accountRepositories.findAll();
    }

    @Override
    public Account findOne(Long id) {
        return accountRepositories.findById(id).get();
    }

    @Override
    public Account findAccountsByEmail(String username) {
        return accountRepositories.findAccountsByEmail(username);
    }

    @Override
    public Iterable<Account> findAllByNameContaining(String name) {
        return accountRepositories.findAllByNameContaining(name);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = this.findAccountsByEmail(username);
        List<GrantedAuthority> rolelist = new ArrayList<>();
//        rolelist.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return new User(account.getEmail(), account.getPassword(),rolelist);
    }

    @Override
    public Account findAccountByEmail(String email){
        return this.accountRepositories.findAccountsByEmail(email);
    }

    @Override
    public Boolean existsAccountByEmail(String email) {
        return this.accountRepositories.existsAccountByEmail(email);
    }

    @Override
    public List<Account> getAllAccountLikedThisStatus(Long statusId) {
        return accountRepositories.findAllAccountLikedByStatusId(statusId);
    }

    @Override
    public List<Account> getAllAccountLikedThisComment(Long comment_id) {
        return accountRepositories.findAllAccountLikedByCommentId(comment_id);
    }

}
