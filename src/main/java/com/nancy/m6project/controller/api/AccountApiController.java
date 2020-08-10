package com.nancy.m6project.controller.api;

import com.nancy.m6project.jwt.JwtUntil;
import com.nancy.m6project.model.account.Account;
import com.nancy.m6project.model.account.AuthRequest;
import com.nancy.m6project.service.impl.AccountServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:4200")
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
    public String generateToken(@RequestBody AuthRequest authRequest){
        String message = "";
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(),
                            authRequest.getPassword())
            );
            message = jwtUntil.generateToken(authRequest.getEmail());
        }catch(ExpiredJwtException jwtExpired){
            message = "Phiên làm việc hết hạn";
        }catch (Exception ex){
            message = "Invalid Email or password";
        }
        return message;
    }

    @PostMapping("/register")
    public String saveAccount(@RequestBody Account newAccount){
        String message = "";
        Account existAccount = accountService.findUsersByEmail(newAccount.getEmail());
       try {
           if (existAccount == null || !newAccount.getEmail().equals(existAccount.getEmail()) ){
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
}
