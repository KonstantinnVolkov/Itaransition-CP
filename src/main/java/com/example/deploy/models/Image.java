package com.example.deploy.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "images")
@Data
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long image_id;

    @Column(name = "post_id")
    private Long post_id;

    @Column(name = "link")
    private String link;

}
