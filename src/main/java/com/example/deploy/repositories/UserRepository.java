package com.example.deploy.repositories;

import com.example.deploy.models.Role;
import com.example.deploy.models.State;
import com.example.deploy.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Transactional
    @Modifying
    @Query(value = "update User set role = :role where id = :id")
    void updateUserRights(@Param("id") long id, @Param("role") Role role);

    @Transactional
    @Modifying
    @Query(value = "update User set state = :state where id = :id")
    void updateUserStatus(@Param("id") long id, @Param("state") State state);

    User findByUserName(String username);
    List<User> findAllByOrderByIdAsc();
    Optional<User> findById(long id);
}
