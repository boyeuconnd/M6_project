package com.nancy.m6project.model.account;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
    private String email;

    private String password;
}
