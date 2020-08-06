package com.nancy.m6project.model.account;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "accounts")
public class Accounts {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false)
    private String password;
}
