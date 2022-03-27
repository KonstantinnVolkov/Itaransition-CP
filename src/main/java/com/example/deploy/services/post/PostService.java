package com.example.deploy.services.post;

import com.example.deploy.DTO.post.PostEditorDTO;
import com.example.deploy.DTO.post.PostProfileDTO;
import com.example.deploy.models.Post;
import com.example.deploy.models.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.io.IOException;
import java.util.List;

public interface PostService {

    void update(long post_id ,PostEditorDTO updatedPost);

    List<Post> getAllPostsByAuthorId(long user_id);
    Post getPostById(Long post_id);
    List<Post> getPostByDateDesc();
    void save(PostProfileDTO postForm, User user) throws IOException;
    void delete(long post_id);
}
