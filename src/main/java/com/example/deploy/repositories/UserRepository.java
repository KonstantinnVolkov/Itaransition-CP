package com.example.deploy.repositories;

import com.example.deploy.models.Role;
import com.example.deploy.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Transactional
    @Modifying
    @Query(value = "update User set role = :role where id = :id")
    void updateUserRights(@Param("id") long id, @Param("role") Role role);

    User findByUserName(String username);
    Optional<User> findById(long id);
}
