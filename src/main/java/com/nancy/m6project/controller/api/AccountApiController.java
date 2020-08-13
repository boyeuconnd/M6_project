package com.nancy.m6project.controller.api;

import com.nancy.m6project.jwt.JwtUntil;
import com.nancy.m6project.model.account.Account;
import com.nancy.m6project.model.account.AuthRequest;
import com.nancy.m6project.model.account.HttpResponse;
import com.nancy.m6project.model.response.ResultResponse;
import com.nancy.m6project.service.impl.AccountServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AccountApiController {
    @Autowired
    private JwtUntil jwtUntil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AccountServiceImpl accountService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/api/account")
    public Iterable<Account> getAll(){
        return accountService.findAll();
    }


    @PostMapping("/login")
    public HttpResponse generateToken(@RequestBody AuthRequest authRequest){
        HttpResponse response = new HttpResponse();
        Account resposeAccount = this.accountService.findAccountByEmail(authRequest.getEmail());
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(),
                            authRequest.getPassword())
            );
            response.setMessage("Login success");
            response.setToken(jwtUntil.generateToken(authRequest.getEmail()));
            resposeAccount.setEmail(authRequest.getEmail());
            response.setAccount_id(resposeAccount.getId());
        }catch(ExpiredJwtException jwtExpired){
            response.setMessage("Phiên làm việc hết hạn");
        }catch (Exception ex){
            response.setMessage("Email hoặc mật khẩu không đúng");
        }
        return response;
    }

    @PostMapping("/register")
    public ResultResponse saveAccount(@RequestBody @Validated Account newAccount){
        ResultResponse response = new ResultResponse();
        Boolean isAccountExist = this.accountService.existsAccountByEmail(newAccount.getEmail());
       try {
           if (!isAccountExist){
               newAccount.setPassword(passwordEncoder.encode(newAccount.getPassword()));
               accountService.save(newAccount);
               response.setMessage("Đăng ký thành công");
           }else {
               response.setMessage("Email đã tồn tại !");
           }
       }catch (Exception e){
           response.setMessage("Lỗi email hoặc mật khẩu chưa nhập !");
       }
        return response;
    }

    @GetMapping("/api/account-details/{id}")
    public Account accountDetails(@PathVariable Long id){
        Account account = accountService.findOne(id);
        return account;
    }
    @PutMapping("api/edit/{id}")
    public HttpResponse editInfomation(@RequestBody Account account){
        HttpResponse response = new HttpResponse();
        Account editedAccount = accountService.save(account);
        if(editedAccount != null){
            response.setMessage("Success");
        }else {
            response.setMessage("Fail");
        }
        return response;
    }
    @PatchMapping("api/find-list-users")
    public Iterable<Account> findAllUserByName(@RequestBody String keyword){
        Iterable<Account> listResult = accountService.findAllByNameContaining(keyword);
        return listResult;
    }



}
