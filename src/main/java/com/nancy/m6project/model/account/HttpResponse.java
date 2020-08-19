package com.nancy.m6project.model.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HttpResponse {
    private String message;
    private Long account_id;
    private String token;

}
