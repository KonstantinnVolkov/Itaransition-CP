package com.example.deploy.repositories;

import com.example.deploy.models.Comment;
import com.example.deploy.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
