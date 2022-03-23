package com.example.deploy.repositories;

import com.example.deploy.models.Post;
import com.example.deploy.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByAuthor_Id(Long author_id);
    List<Post> findAllByOrderByPostedDesc();
    List<Post> findByTags(String tag);

//    List<Post> findByAuthor_Id(Long authorID);


}
