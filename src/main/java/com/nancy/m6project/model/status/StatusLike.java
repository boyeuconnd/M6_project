package com.nancy.m6project.model.status;

import com.nancy.m6project.model.account.Account;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "status_likes")
public class StatusLike {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Boolean isStatusLike = false;

    @Column(name = "created_date", nullable = false)
    private Timestamp createdDate = Timestamp.valueOf(LocalDateTime.now());

    @Column(name = "modify_date", nullable = false)
    private Timestamp modifyDate = Timestamp.valueOf(LocalDateTime.now());

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;
}
