package com.example.deploy.services;

import com.example.deploy.DTO.post.PostProfileDTO;
import com.example.deploy.models.Post;
import com.example.deploy.models.User;

import java.util.List;

public interface PostService {

    List<Post> getAllPostsByAuthorId(long user_id);
    Post getPostById(Long post_id);
    List<Post> getPostByDateDesc();
    void save(PostProfileDTO postForm, User user);
    public void updatePost(Post updatedPost);
    void delete(long post_id);
}
