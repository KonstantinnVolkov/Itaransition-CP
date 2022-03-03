package com.example.deploy.forms;

import lombok.Data;

@Data
public class UserForm {

    private String email;
    private String userName;
    private String password;
    private transient String confirmPassword;
}
