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

    @Column(columnDefinition = "varchar(190) default 'https://firebasestorage.googleapis.com/v0/b/final-project-dangpham.appspot.com/o/avatar%2Favatar-default.png?alt=media&token=c6aefd28-07a0-49ce-9507-2885ef64df68'")
    private String avatarUrl;

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
