package com.nancy.m6project.model.friendRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nancy.m6project.model.account.Account;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "friend_request")
public class FriendRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "create_date", nullable = false)
    private Timestamp createDate = Timestamp.valueOf(LocalDateTime.now());

    @Column(name = "modify_date", nullable = false)
    private Timestamp modifiedDate = Timestamp.valueOf(LocalDateTime.now());

    @ManyToOne
    @JoinColumn(name = "account_send", referencedColumnName = "id", nullable = false)
    private Account accountSend;

    @ManyToOne
    @JoinColumn(name = "account_receive", referencedColumnName = "id", nullable = false)
    private Account accountReceive;
}
