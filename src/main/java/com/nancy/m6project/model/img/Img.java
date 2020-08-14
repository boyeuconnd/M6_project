package com.nancy.m6project.model.img;

import com.nancy.m6project.model.status.Status;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "images")
public class Img {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(columnDefinition = "text")
    private String url;


}
