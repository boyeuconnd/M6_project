package com.nancy.m6project.model.status;

import com.nancy.m6project.model.account.Account;
import com.nancy.m6project.model.comment.Comment;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@Table(name = "statuses")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(columnDefinition = "text")
    private String content;

    private Timestamp createDate = Timestamp.valueOf(LocalDateTime.now());

    private Integer totalLikes;

    private Integer totalComments;

    @OneToMany(mappedBy = "status", fetch = FetchType.EAGER)
    private Set<Comment> comments;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", nullable = false)
    private Account account;

}
