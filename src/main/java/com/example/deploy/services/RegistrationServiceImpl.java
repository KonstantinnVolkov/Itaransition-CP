package com.example.deploy.services;

import com.example.deploy.DTO.user.UserRegistrationDTO;
import com.example.deploy.mappers.UserMapper;
import com.example.deploy.models.Role;
import com.example.deploy.models.State;
import com.example.deploy.models.User;
import com.example.deploy.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.example.deploy.mappers.UserMapper.mapRegistrationDTO_ToEntity;

@Service
public class RegistrationServiceImpl implements RegistrationService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(UserRegistrationDTO userRegistrationDTO) {
        String encodedPassword = passwordEncoder.encode(userRegistrationDTO.getPassword());
        userRepository.save(UserMapper.mapRegistrationDTO_ToEntity(userRegistrationDTO, encodedPassword));
    }
}
