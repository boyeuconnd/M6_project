package com.nancy.m6project.model.status;

import com.nancy.m6project.model.account.Accounts;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    private String content;

    @ManyToOne
    private Status status;

    @ManyToOne
    private Accounts accounts;
}
