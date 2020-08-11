package com.nancy.m6project.controller.api;

import com.nancy.m6project.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

public class FriendApiController {
    @Autowired
    AccountService accountService;

    @PostMapping
    public void sendFriendRequest(){

    }
}
