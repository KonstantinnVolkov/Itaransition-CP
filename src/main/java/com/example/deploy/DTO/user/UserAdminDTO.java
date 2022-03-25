package com.example.deploy.DTO.user;

import com.example.deploy.models.Role;
import com.example.deploy.models.State;
import lombok.Data;

@Data
public class UserAdminDTO {
    private long id;
    private String email;
    private String username;
    private Role role;
    private State state;
}
