package com.example.deploy.services;

import com.example.deploy.details.UserDetailsImpl;
import com.example.deploy.models.User;
import com.example.deploy.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if (user == null){
            throw  new UsernameNotFoundException(String.format("User '%s' not found", username));
        }
        return new UserDetailsImpl(user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
