package com.example.deploy.DTO.post;

import com.example.deploy.models.Comment;
import lombok.Data;

import java.util.Collection;

@Data
public class PostProfileDTO {
    private Long post_id;
    private String tags;
    private String theme;
    private String body;
    private Double rate;
    private Collection<Comment> comments;
}
