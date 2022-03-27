package com.example.deploy.DTO.post;

import lombok.Data;

@Data
public class PostEditorDTO {
    private long post_id;
    private String tags;
    private String theme;
    private String body;
    private long author_id;
    private String author_username;
}
