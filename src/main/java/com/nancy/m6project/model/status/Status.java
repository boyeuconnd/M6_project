package com.nancy.m6project.model.status;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nancy.m6project.model.account.Account;
import com.nancy.m6project.model.comment.Comment;
import com.nancy.m6project.model.img.Img;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
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

    @Column(name = "create_date", nullable = false)
    private Timestamp createDate = Timestamp.valueOf(LocalDateTime.now());

    @Column(name = "modify_date", nullable = false)
    private Timestamp modifyDate = Timestamp.valueOf(LocalDateTime.now());

    private Integer totalLikes = 0;

    private Integer totalComments = 0;

    @OneToMany
    private List<Img> images;

    @OneToMany(mappedBy = "status", fetch = FetchType.EAGER)
    private Set<Comment> comments;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", nullable = false)
    private Account account;

    @OneToMany(mappedBy = "status", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<StatusLike> likes;

}
