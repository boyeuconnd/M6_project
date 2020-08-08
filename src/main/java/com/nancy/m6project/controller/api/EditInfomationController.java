package com.nancy.m6project.controller.api;

import com.nancy.m6project.model.account.Account;
import com.nancy.m6project.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EditInfomationController {
    @Autowired
    private AccountService accountService;
    @PostMapping("api/edit/{id}")
    public ResponseEntity<Void> editInfomation(@RequestBody Account account){
        accountService.save(account);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
