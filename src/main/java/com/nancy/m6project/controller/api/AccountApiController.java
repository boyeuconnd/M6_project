package com.nancy.m6project.controller.api;

import com.nancy.m6project.jwt.JwtUntil;
import com.nancy.m6project.model.account.Account;
import com.nancy.m6project.model.account.AuthRequest;
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
//@RequestMapping("api")
//@CrossOrigin(origins = "http://localhost:4200")
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
    public Map<String,String> generateToken(@RequestBody AuthRequest authRequest){
        HashMap<String,String> response = new HashMap<>();
        String message = "";
        Long userId;
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(),
                            authRequest.getPassword())
            );
            message = "Login success";
            response.put("token",jwtUntil.generateToken(authRequest.getEmail()));
            response.put("email",authRequest.getEmail());
        }catch(ExpiredJwtException jwtExpired){
            message = "Phiên làm việc hết hạn";
        }catch (Exception ex){
            message = "Invalid Email or password";
        }
        response.put("message",message);
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
               message = "Đăng ký thành công !!!";
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
        account.setPassword("hidden");
        return account;
    }
    @PostMapping("api/edit/{id}")
    public ResponseEntity<Void> editInfomation(@RequestBody Account account){
        accountService.save(account);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PostMapping("api/find-list-users")
    public Iterable<Account> findAllUserByName(@RequestBody String name){
        Iterable<Account> listResult = accountService.findAllByNameContaining(name);
        for (Account account: listResult
             ) {
            account.setPassword("hidden");
        }
        return listResult;
    }
    @GetMapping("api/find-one-user/{id}")
    public Account findOneUserByName(@RequestParam Long id){
        Account account = accountService.findOne(id);

        return account;
    }


}
