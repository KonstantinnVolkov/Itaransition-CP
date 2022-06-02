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

    public UserAdminDTO() {
    }

    public UserAdminDTO(long id, String email, String username, Role role,
                        State state) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.role = role;
        this.state = state;
    }
}
