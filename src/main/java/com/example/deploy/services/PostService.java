package com.example.deploy.services;

import com.example.deploy.forms.PostForm;
import com.example.deploy.models.Post;
import com.example.deploy.models.User;
import com.example.deploy.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void save(PostForm postForm, User user) {
        postRepository.save(convertFormToEntity(postForm, user));
    }

    public Post getPostById(Long post_id) {
        Optional<Post> postOptional = postRepository.findById(post_id);
        Post post = null;
        if (postOptional.isPresent()) {
            post = postOptional.get();
        }
        else {
            throw new RuntimeException("Post for id" + post_id + "not found!");
        }
        return post;
    }

    public void delete(long post_id){
        Optional<Post> postOptional = postRepository.findById(post_id);
        if (postOptional.isPresent()) {
            postRepository.delete(postOptional.get());
        }
        else {
            throw new RuntimeException("Post for id " + post_id + " not found!");
        }

    }

    public void updatePost(Post updatedPost){
        postRepository.delete(getPostById(updatedPost.getPost_id()));
        postRepository.save(updatedPost);
    }

    private Post convertFormToEntity(PostForm postForm, User user) {
        Post post = new Post();
        post.setAuthor(user);
        post.setTags(postForm.getTags());
        post.setTheme(postForm.getTheme());
        post.setBody(postForm.getBody());
        post.setRate(0D);
        post.setPosted(new Date());
        return post;
    }


}
