package com.example.deploy.mappers;

import com.example.deploy.DTO.post.PostDTO;
import com.example.deploy.models.Post;
import com.example.deploy.models.User;

import java.util.Date;

public class PostMapper {

    public static Post mapDTO_toEntity(PostDTO postDTO, User user){
        Post post = new Post();
        post.setAuthor(user);
        post.setTags(postDTO.getTags());
        post.setTheme(postDTO.getTheme());
        post.setBody(postDTO.getBody());
        post.setRate(0D);
        post.setPosted(new Date());
        return post;
    }
}
