package com.example.deploy.DTO.post;

import com.example.deploy.models.User;
import lombok.Data;

@Data
public class PostDTO {
    private Long post_id;
    private String tags;
    private String theme;
    private String body;
    private Double rate;
}
