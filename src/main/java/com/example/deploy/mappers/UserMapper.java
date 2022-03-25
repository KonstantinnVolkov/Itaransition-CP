package com.example.deploy.mappers;

import com.example.deploy.DTO.user.UserRegistrationDTO;
import com.example.deploy.models.Role;
import com.example.deploy.models.State;
import com.example.deploy.models.User;

public class UserMapper {

    public static User mapRegistrationDTO_ToEntity(UserRegistrationDTO userRegistrationDTO,
                                                   String encodedPassword){
        return User.builder()
                .email(userRegistrationDTO.getEmail())
                .userName(userRegistrationDTO.getUserName())
                .password(encodedPassword)
                .role(Role.USER)
                .state(State.ACTIVE)
                .build();
    }
}
