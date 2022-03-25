package com.example.deploy.DTO.post;

import lombok.Data;

@Data
public class PostDTO {
    private String tags;
    private String theme;
    private String body;
    private Double rate;
}
