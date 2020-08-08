package com.nancy.m6project.model.account;

import com.nancy.m6project.model.status.Comment;
import com.nancy.m6project.model.status.Status;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    private String address;

    private Long phoneNumber;

    private Date dateOfBirth;

    @ManyToOne
    private Gender gender;

    @OneToMany
    private Set<Comment> comment;

    @OneToMany
    private Set<Status> status;
}
