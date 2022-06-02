package com.example.deploy.DTO.post;

import com.example.deploy.models.Comment;
import com.example.deploy.models.Image;
import com.example.deploy.models.User;
import lombok.Data;

import java.util.Collection;

@Data
public class PostFeedDTO {

    private Long post_id;
    private String tags;
    private String theme;
    private String body;
    private Double rate;
    private User author;
    private Collection<Image> images;
    private Collection<Comment> comments;

    public PostFeedDTO() {
    }

    public PostFeedDTO(Long post_id, String tags, String theme, String body,
                       Double rate, User author, Collection<Image> images,
                       Collection<Comment> comments) {
        this.post_id = post_id;
        this.tags = tags;
        this.theme = theme;
        this.body = body;
        this.rate = rate;
        this.author = author;
        this.images = images;
        this.comments = comments;
    }
}
