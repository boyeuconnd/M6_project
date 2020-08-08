package com.nancy.m6project.model.comment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nancy.m6project.model.account.Account;
import com.nancy.m6project.model.status.Status;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String content;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private Status status;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", nullable = false)
    private Account account;
}
