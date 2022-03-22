package com.example.deploy.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "posts")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long post_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private User author;

    @Column(name = "tag")
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
