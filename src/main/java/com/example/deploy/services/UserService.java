package com.example.deploy.services;

import com.example.deploy.models.User;
import com.example.deploy.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserByUsername(String username){
        return userRepository.findByUserName(username);
    }

}
