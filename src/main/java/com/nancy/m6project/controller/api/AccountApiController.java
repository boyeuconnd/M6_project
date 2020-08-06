package com.nancy.m6project.controller.api;

import com.nancy.m6project.jwt.JwtUntil;
import com.nancy.m6project.model.account.Accounts;
import com.nancy.m6project.model.account.AuthRequest;
import com.nancy.m6project.service.impl.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class AccountApiController {
    @Autowired
    private JwtUntil jwtUntil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AccountServiceImpl accountService;

    @GetMapping("")
    public Iterable<Accounts> getAll(){
        return accountService.findAll();
    }

//    @PostMapping("/login")
//    public
    @PostMapping("/authen")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception{
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(),
                            authRequest.getPassword())
            );

        }catch (Exception ex){
            throw new Exception("Invalid Email or password");
        }
        return jwtUntil.generateToken(authRequest.getEmail());
    }


}
