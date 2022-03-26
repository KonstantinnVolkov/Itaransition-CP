package com.example.deploy.mappers;

import com.example.deploy.DTO.post.PostFeedDTO;
import com.example.deploy.DTO.post.PostProfileDTO;
import com.example.deploy.models.Post;
import com.example.deploy.models.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostMapper {

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

    public static List<PostProfileDTO> mapEntityToProfileDTO(List<Post> usersPosts){
        List<PostProfileDTO> postProfileDTOList = new ArrayList<>();
        for (Post post: usersPosts) {
            PostProfileDTO postProfileDTO = new PostProfileDTO();
            postProfileDTO.setPost_id(post.getPost_id());
            postProfileDTO.setTags(post.getTags());
            postProfileDTO.setTheme(post.getTheme());
            postProfileDTO.setBody(post.getBody());
            postProfileDTO.setRate(post.getRate());
            postProfileDTO.setComments(post.getComments());
            postProfileDTOList.add(postProfileDTO);
        }
        return postProfileDTOList;
    }

    public static List<PostFeedDTO> mapEntityToFeedDTO(List<Post> usersPosts){
        List<PostFeedDTO> postFeedDTOList = new ArrayList<>();
        for (Post post: usersPosts) {
            PostFeedDTO postFeedDTO = new PostFeedDTO();
            postFeedDTO.setPost_id(post.getPost_id());
            postFeedDTO.setTags(post.getTags());
            postFeedDTO.setTheme(post.getTheme());
            postFeedDTO.setBody(post.getBody());
            postFeedDTO.setRate(post.getRate());
            postFeedDTO.setAuthor(post.getAuthor());
            postFeedDTO.setComments(post.getComments());
            postFeedDTOList.add(postFeedDTO);
        }
        return postFeedDTOList;
    }



}
