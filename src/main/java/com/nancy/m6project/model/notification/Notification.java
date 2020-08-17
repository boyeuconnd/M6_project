package com.nancy.m6project.model.notification;

import com.nancy.m6project.model.account.Account;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String type;

    private boolean isSeen = false;

    @Column(name = "create_date", nullable = false)
    private Timestamp createDate = Timestamp.valueOf(LocalDateTime.now());

    @ManyToOne
    @JoinColumn(name = "account_send", referencedColumnName = "id", nullable = false)
    private Account accountSend;

    @ManyToOne
    @JoinColumn(name = "account_receive", referencedColumnName = "id", nullable = false)
    private Account accountReceive;
}
