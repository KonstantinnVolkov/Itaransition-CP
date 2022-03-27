package com.example.deploy.services.post;

import com.example.deploy.DTO.post.PostEditorDTO;
import com.example.deploy.DTO.post.PostProfileDTO;
import com.example.deploy.mappers.PostMapper;
import com.example.deploy.models.Post;
import com.example.deploy.models.User;
import com.example.deploy.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> getAllPostsByAuthorId(long user_id){
        return postRepository.findAllByAuthor_Id(user_id);
    }

    @Override
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

    @Override
    public List<Post> getPostByDateDesc(){
       return postRepository.findAllByOrderByPostedDesc();
    }

    @Override
    public void save(PostProfileDTO postForm, User user) {
        postRepository.save(PostMapper.mapDTO_toEntity(postForm, user));
    }

    @Override
    public void delete(long post_id){
        Optional<Post> postOptional = postRepository.findById(post_id);
        if (postOptional.isPresent()) {
            postRepository.delete(postOptional.get());
        }
        else {
            throw new RuntimeException("Post for id " + post_id + " not found!");
        }
    }

    @Override
    public void update(long post_id, PostEditorDTO updatedPost){
        postRepository.updatePostById(updatedPost.getPost_id(), updatedPost.getTags(),
                                      updatedPost.getTheme(), updatedPost.getBody());

    }

}
