package com.example.deploy.mappers;

import com.example.deploy.DTO.post.PostDTO;
import com.example.deploy.models.Post;
import com.example.deploy.models.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public static List<PostDTO> mapEntityToDTO(List<Post> usersPosts){
        List<PostDTO> postDTOList = new ArrayList<>();
        for (Post post: usersPosts) {
            PostDTO postDTO = new PostDTO();
            postDTO.setPost_id(post.getPost_id());
            postDTO.setTags(post.getTags());
            postDTO.setTheme(post.getTheme());
            postDTO.setBody(post.getBody());
            postDTO.setRate(post.getRate());
            postDTOList.add(postDTO);
        }
        return postDTOList;
    }

}
