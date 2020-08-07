package com.nancy.m6project.model.account;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@Table(name = "accounts")
public class Accounts {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Basic
    @Column(nullable = false, unique = true)
    private String email;

    @Basic
    @Column(nullable = false)
    private String name;

    @Basic
    @Column(nullable = false)
    private String password;

    @Basic
    private String address;

    @Basic
    private String phoneNumber;

    private Date dateOfBirth;

    @ManyToOne
    @JoinColumn
    private Gender gender;
}
