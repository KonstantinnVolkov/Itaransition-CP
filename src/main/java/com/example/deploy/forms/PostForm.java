package com.example.deploy.forms;

import lombok.Data;

@Data
public class PostForm {
    private String tags;
    private String theme;
    private String body;
    private Double rate;
}
