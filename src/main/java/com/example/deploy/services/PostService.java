package com.example.deploy.services;

import com.example.deploy.forms.PostForm;
import com.example.deploy.models.Post;
import com.example.deploy.models.User;
import com.example.deploy.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void save(PostForm postForm, User user) {
        Post post = new Post();
        post.setAuthor(user);
        post.setTag(postForm.getTag());
        post.setTheme(postForm.getTheme());
        post.setBody(postForm.getBody());
        post.setRate(0D);
        postRepository.save(post);
    }
}