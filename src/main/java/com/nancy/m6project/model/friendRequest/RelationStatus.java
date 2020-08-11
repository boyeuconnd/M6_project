package com.nancy.m6project.model.friendRequest;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name= "relation_status")
public class RelationStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
}
