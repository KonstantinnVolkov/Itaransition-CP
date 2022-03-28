package com.example.deploy.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "comments")
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comment_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private User author;

    @Column(name = "post_id")
    private Long post_id;

    @Column(name = "comment")
    private String comment;

    @Column(name = "posted", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date posted;

}
