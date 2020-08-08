package com.nancy.m6project.service.impl;

import com.nancy.m6project.model.account.Accounts;
import com.nancy.m6project.repositories.account.AccountRepositories;
import com.nancy.m6project.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
    public Accounts save(Accounts accounts) {
        return accountRepositories.save( accounts);
    }

    @Override
    public Iterable<Accounts> findAll() {
        return accountRepositories.findAll();
    }

    @Override
    public Accounts findOne(Long id) {
        return accountRepositories.findById(id).get();
    }

    @Override
    public Accounts findUsersByEmail(String username) {
        return accountRepositories.findUsersByEmail(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Accounts accounts = this.findUsersByEmail(username);
        List<GrantedAuthority> rolelist = new ArrayList<>();
//        rolelist.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return new User(accounts.getEmail(),accounts.getPassword(),rolelist);
    }

}
