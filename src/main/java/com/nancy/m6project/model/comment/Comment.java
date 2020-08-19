package com.nancy.m6project.model.comment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nancy.m6project.model.account.Account;
import com.nancy.m6project.model.status.Status;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String content;

    @Column(name = "created_date", nullable = false)
    private Timestamp createdDate = Timestamp.valueOf(LocalDateTime.now());

    @Column(name = "modify_date", nullable = false)
    private Timestamp modifyDate = Timestamp.valueOf(LocalDateTime.now());

    private Integer totalLikes = 0;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", nullable = false)
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private Status status;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", nullable = false)
    @EqualsAndHashCode.Exclude
    private Account account;

    @OneToMany(mappedBy = "id",cascade = CascadeType.ALL)
//    @ToString.Exclude
    private Set<CommentLike> commentLikes;
}
