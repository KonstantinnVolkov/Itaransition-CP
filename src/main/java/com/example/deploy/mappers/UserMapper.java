package com.example.deploy.mappers;

import com.example.deploy.DTO.user.UserAdminDTO;
import com.example.deploy.DTO.user.UserProfileDTO;
import com.example.deploy.DTO.user.UserRegistrationDTO;
import com.example.deploy.models.Role;
import com.example.deploy.models.State;
import com.example.deploy.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public static UserProfileDTO mapEntityToProfileDTO(User user){
        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setUser_id(user.getId());
        userProfileDTO.setUsername(user.getUserName());
        return userProfileDTO;
    }

    public static List<UserAdminDTO> mapEntityToUserAdminDTO(List<User> allUsers){
        return allUsers.stream().map(user ->
                new UserAdminDTO(
                        user.getId(),
                        user.getEmail(),
                        user.getUserName(),
                        user.getRole(),
                        user.getState()
                )
        ).collect(Collectors.toList());
    }
}
