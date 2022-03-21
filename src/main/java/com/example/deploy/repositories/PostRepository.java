package com.example.deploy.repositories;

import com.example.deploy.models.Post;
import com.example.deploy.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAll();
    List<Post> findByTag(String tag);

    List<Post> findByAuthor_Id(Long authorID);


}
