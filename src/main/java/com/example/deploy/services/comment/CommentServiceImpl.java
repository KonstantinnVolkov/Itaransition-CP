package com.example.deploy.services.comment;

import com.example.deploy.mappers.CommentMapper;
import com.example.deploy.models.User;
import com.example.deploy.repositories.CommentRepository;
import com.example.deploy.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final UserService userService;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, UserService userService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
    }

    @Override
    public void save(long post_id, long author_id, String commentText) {
        User author = userService.getUserById(author_id);
        commentRepository.save(CommentMapper.mapDTO_toEntity(author, post_id, commentText));
    }
}
