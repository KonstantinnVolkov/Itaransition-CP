package com.example.deploy.services;

import com.example.deploy.models.Comment;
import com.example.deploy.models.Post;
import com.example.deploy.models.User;
import com.example.deploy.repositories.CommentRepository;
import com.example.deploy.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostServiceImpl postService;
    private final UserRepository userRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository,
                          PostServiceImpl postService,
                          UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postService = postService;
        this.userRepository = userRepository;
    }

    public void saveComment(Long post_id, Long author_id, String comment){
        commentRepository.save(convertFormToEntity(
                postService.getPostById(post_id),
                userRepository.findById(author_id).orElseThrow(),
                comment));
    }

    private Comment convertFormToEntity(Post post, User author, String comment){
        Comment newComment = new Comment();
        newComment.setPost(post);
        newComment.setAuthor(author);
        newComment.setComment(comment);
        return newComment;
    }
}
