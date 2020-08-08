package com.nancy.m6project.model.status;

import com.nancy.m6project.model.account.Accounts;
import lombok.Data;
import org.springframework.context.annotation.EnableMBeanExport;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String content;

    private Timestamp createDate = Timestamp.valueOf(LocalDateTime.now());

    private int like = 0;

    @OneToMany
    private Set<Comment> comments;

    @ManyToOne
    private Accounts accounts;

}
