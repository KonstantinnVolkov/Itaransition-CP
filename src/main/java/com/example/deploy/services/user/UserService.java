package com.example.deploy.services.user;

import com.example.deploy.models.Role;
import com.example.deploy.models.State;
import com.example.deploy.models.User;
import com.example.deploy.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByUsername(String username){
        return userRepository.findByUserName(username);
    }

    public User getUserById(Long user_id){
        Optional<User> userOptional = userRepository.findById(user_id);
        User user;
        if (userOptional.isPresent()) {
            user = userOptional.get();
        }
        else {
            throw new RuntimeException("Post for id" + user_id + "not found!");
        }
        return user;
    }

    public void updateRights(long user_id, Role role){
        userRepository.updateUserRights(user_id, role);
    }
    public void updateStatus(long user_id, State state){
        userRepository.updateUserStatus(user_id, state);
    }
}
