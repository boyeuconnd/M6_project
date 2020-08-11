package com.nancy.m6project.controller.api;

import com.nancy.m6project.jwt.JwtUntil;
import com.nancy.m6project.model.account.Account;
import com.nancy.m6project.model.account.AuthRequest;
import com.nancy.m6project.model.account.HttpResponse;
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
    public String saveAccount(@RequestBody @Validated Account newAccount){
        String message = "";
        Boolean isAccountExist = this.accountService.existsAccountByEmail(newAccount.getEmail());
       try {
           if (!isAccountExist){
               newAccount.setPassword(passwordEncoder.encode(newAccount.getPassword()));
               accountService.save(newAccount);
               message = "Đăng ký thành công";
           }else {
               message = "Email đã tồn tại !" ;
           }
       }catch (Exception e){
           message = "Lỗi email hoặc mật khẩu chưa nhập !";
       }
        return message;
    }

    @GetMapping("/api/account-details/{id}")
    public Account accountDetails(@PathVariable Long id){
        Account account = accountService.findOne(id);
        return account;
    }
    @PutMapping("api/edit/{id}")
    public ResponseEntity<Void> editInfomation(@RequestBody Account account){
        accountService.save(account);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PostMapping("api/find-list-users")
    public Iterable<Account> findAllUserByName(@RequestBody String name){
        Iterable<Account> listResult = accountService.findAllByNameContaining(name);
        for (Account account: listResult
             ) {
        }
        return listResult;
    }
    @GetMapping("api/find-one-user/{id}")
    public Account findOneUserByName(@RequestParam Long id){
        Account account = accountService.findOne(id);

        return account;
    }


}
