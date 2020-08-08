package com.nancy.m6project.controller.api;

import com.nancy.m6project.jwt.JwtUntil;
import com.nancy.m6project.model.account.Accounts;
import com.nancy.m6project.model.account.AuthRequest;
import com.nancy.m6project.service.impl.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@RestController
//@RequestMapping("api")
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
    public Iterable<Accounts> getAll(){
        return accountService.findAll();
    }


    @PostMapping("/login")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception{
        String message = "";
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(),
                            authRequest.getPassword())
            );
            message = jwtUntil.generateToken(authRequest.getEmail());
        }catch (Exception ex){
            message = "Invalid Email or password";
        }
        return message;
    }

    @PostMapping("/register")
    public String saveAccount(@RequestBody Accounts newAccounts){
        String message = "";
        Accounts existAccount = accountService.findUsersByEmail(newAccounts.getEmail());
       try {
           if (existAccount == null || !newAccounts.getEmail().equals(existAccount.getEmail()) ){
               newAccounts.setPassword(passwordEncoder.encode(newAccounts.getPassword()));
               accountService.save(newAccounts);
               message = "Đăng ký thành công !!!";
           }else {
               message = "Email đã tồn tại !" ;
           }
       }catch (Exception e){
           message = "Lỗi email hoặc mật khẩu chưa nhập !";
       }
        return message;
    }
}
