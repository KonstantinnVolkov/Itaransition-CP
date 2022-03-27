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
}
