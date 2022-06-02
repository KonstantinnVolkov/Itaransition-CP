package com.example.deploy.services.post;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.deploy.DTO.post.PostEditorDTO;
import com.example.deploy.DTO.post.PostProfileDTO;
import com.example.deploy.mappers.PostMapper;
import com.example.deploy.models.Image;
import com.example.deploy.models.Post;
import com.example.deploy.models.User;
import com.example.deploy.repositories.ImageRepository;
import com.example.deploy.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.print.Pageable;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final ImageRepository imageRepository;
    private final Cloudinary cloudinary;
    private final int POST_AMOUNT = 10;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, ImageRepository imageRepository, Cloudinary cloudinary) {
        this.postRepository = postRepository;
        this.imageRepository = imageRepository;
        this.cloudinary = cloudinary;
    }

    @Override
    public List<Post> getAllPostsByAuthorId(long user_id){
        return postRepository.findAllByAuthor_IdOrderByPostedDesc(user_id);
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
    public void save(PostProfileDTO postForm, User user) throws IOException {
        postRepository.save(PostMapper.mapDTO_toEntity(postForm, user));
        long post_id = postRepository.findPostByAuthorAndAndBody(user, postForm.getBody()).getPost_id();

        for (MultipartFile image : postForm.getImages()) {
            if (image.getBytes().length == 0)
                break;
            Map uploadResult = cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap());
            Image imageToSave = new Image();
            imageToSave.setPost_id(post_id);
            imageToSave.setLink(String.valueOf(uploadResult.get("url")));
            imageRepository.save(imageToSave);
        }
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

    @Override
    public List<Post> getPostsSortedByTag(String tag) {
        return postRepository.findAllByTagsOrderByPostedDesc(tag);
    }
}
