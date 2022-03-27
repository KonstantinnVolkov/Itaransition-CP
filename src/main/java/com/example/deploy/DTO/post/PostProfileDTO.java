package com.example.deploy.DTO.post;

import com.example.deploy.models.Comment;
import com.example.deploy.models.Image;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

@Data
public class PostProfileDTO {
    private Long post_id;
    private String tags;
    private String theme;
    private String body;
    private Double rate;
    private Collection<Comment> comments;
    private Collection<MultipartFile> images;
    private Collection<Image> imagesToShow;
}
