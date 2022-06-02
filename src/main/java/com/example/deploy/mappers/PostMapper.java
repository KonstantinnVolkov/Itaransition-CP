package com.example.deploy.mappers;

import com.example.deploy.DTO.post.PostEditorDTO;
import com.example.deploy.DTO.post.PostFeedDTO;
import com.example.deploy.DTO.post.PostProfileDTO;
import com.example.deploy.models.Post;
import com.example.deploy.models.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class PostMapper {

    public static List<PostProfileDTO> mapEntityToProfileDTO(List<Post> usersPosts){
        return usersPosts.stream().map(post ->
                        new PostProfileDTO(
                                post.getPost_id(),
                                post.getTags(),
                                post.getTheme(),
                                post.getBody(),
                                post.getRate(),
                                post.getComments(),
                                post.getImages()
                        )
                ).collect(Collectors.toList());
    }

    public static List<PostFeedDTO> mapEntityToFeedDTO(List<Post> usersPosts){
        return usersPosts.stream().map(post ->
                        new PostFeedDTO(
                                post.getPost_id(),
                                post.getTags(),
                                post.getTheme(),
                                post.getBody(),
                                post.getRate(),
                                post.getAuthor(),
                                post.getImages(),
                                post.getComments()
                        )
                ).collect(Collectors.toList());
    }

    public static Post mapDTO_toEntity(PostProfileDTO postDTO, User user){
        Post post = new Post();
        post.setAuthor(user);
        post.setTags(postDTO.getTags());
        post.setTheme(postDTO.getTheme());
        post.setBody(postDTO.getBody());
        post.setRate(0D);
        post.setPosted(new Date());
        return post;
    }

    public static PostEditorDTO mapEntityToEditorDTO(Post post){
        PostEditorDTO postEditorDTO = new PostEditorDTO();
        postEditorDTO.setPost_id(post.getPost_id());
        postEditorDTO.setTags(post.getTags());
        postEditorDTO.setTheme(post.getTheme());
        postEditorDTO.setBody(post.getBody());
        postEditorDTO.setAuthor_id(post.getAuthor().getId());
        postEditorDTO.setAuthor_username(post.getAuthor().getUserName());
        return postEditorDTO;
    }
}
