package com.nancy.m6project.model.comment;

import com.nancy.m6project.model.account.Account;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Data
@Table(name = "comment_likes")
public class CommentLike {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private boolean isCommentLike = false;

    @Column(name = "created_date", nullable = false)
    private Timestamp createdDate;

    @Column(name = "modify_date", nullable = false)
    private Timestamp modifyDate;

    @ManyToOne
    @JoinColumn(name = "comment_id", referencedColumnName = "id", nullable = false)
    private Comment comment;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false)
    private Account account;

}
