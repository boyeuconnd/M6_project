package com.nancy.m6project.model.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nancy.m6project.model.comment.Comment;
import com.nancy.m6project.model.friendRequest.FriendRequest;
import com.nancy.m6project.model.status.Status;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
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

    @OneToMany(mappedBy = "account")
    @JsonIgnore
    private Set<Comment> comments;

    @OneToMany(mappedBy = "account")
    @JsonIgnore
    private Set<Status> statuses;

    @OneToMany(mappedBy = "accountSend")
    @JsonIgnore
    private Set<FriendRequest> requestsThisUserSent;

    @OneToMany(mappedBy = "accountReceive")
    @JsonIgnore
    private Set<FriendRequest> requestsThisUserReceived;
}
