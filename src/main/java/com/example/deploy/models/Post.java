package com.example.deploy.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "posts")
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long post_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private User author;

    @Column(name = "tags")
    private String tag;

    @Column(name = "theme")
    private String theme;

    @Column(name = "body")
    private String body;

    @Column(name = "posted", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date posted;

    @Column(name = "rate")
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Double rate;

}
