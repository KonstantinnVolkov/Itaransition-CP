package com.example.deploy.services;

import com.example.deploy.forms.UserForm;
import com.example.deploy.models.Role;
import com.example.deploy.models.State;
import com.example.deploy.models.User;
import com.example.deploy.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    public void register(UserForm userForm) {
        String passwordEncoded = passwordEncoder.encode(userForm.getPassword());
        User user = User.builder()
                .email(userForm.getEmail())
                .userName(userForm.getUserName())
                .password(passwordEncoded)
                .role(Role.USER)
                .state(State.ACTIVE)
                .build();
        userRepository.save(user);
    }
}
