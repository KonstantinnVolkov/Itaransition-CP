package com.example.deploy.mappers;

import com.example.deploy.DTO.user.UserAdminDTO;
import com.example.deploy.DTO.user.UserProfileDTO;
import com.example.deploy.DTO.user.UserRegistrationDTO;
import com.example.deploy.models.Role;
import com.example.deploy.models.State;
import com.example.deploy.models.User;

import java.util.ArrayList;
import java.util.List;

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
        List<UserAdminDTO> userAdminDTOList = new ArrayList<>();
        for (User user: allUsers) {
            UserAdminDTO userAdminDTO = new UserAdminDTO();
            userAdminDTO.setId(user.getId());
            userAdminDTO.setEmail(user.getEmail());
            userAdminDTO.setUsername(user.getUserName());
            userAdminDTO.setRole(user.getRole());
            userAdminDTO.setState(user.getState());
            userAdminDTOList.add(userAdminDTO);
        }
        return userAdminDTOList;
    }
}
