package com.nancy.m6project.model.comment;

import com.nancy.m6project.model.account.Account;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;


@Entity
@Data
@Table(name = "comment_likes")
public class CommentLike {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(name = "created_date", nullable = false)
    private Timestamp createdDate = Timestamp.valueOf(LocalDateTime.now());

    @Column(name = "modify_date", nullable = false)
    private Timestamp modifyDate = Timestamp.valueOf(LocalDateTime.now());

    @ManyToOne
    @JoinColumn(name = "comment_id", referencedColumnName = "id", nullable = false)
    private Comment comment;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false)
    private Account account;

}
