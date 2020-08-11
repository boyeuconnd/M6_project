package com.nancy.m6project.model.friendRequest;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class FriendRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
