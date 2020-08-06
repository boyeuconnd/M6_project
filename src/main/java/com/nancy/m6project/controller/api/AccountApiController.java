package com.nancy.m6project.controller.api;

import com.nancy.m6project.model.account.Accounts;
import com.nancy.m6project.service.impl.AccountServiceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/account")
public class AccountApiController {

    @Autowired
    private AccountServiceServiceImpl accountService;

    @GetMapping("")
    public Iterable<Accounts> getAll(){
        return accountService.findAll();
    }

//    @PostMapping("/login")
//    public


}
