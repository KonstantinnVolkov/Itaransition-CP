package com.example.deploy.controllers;

import com.example.deploy.forms.UserForm;
import com.example.deploy.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    private final RegistrationService registrationService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationController(RegistrationService registrationService, PasswordEncoder passwordEncoder) {
        this.registrationService = registrationService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/registration")
    public String getRegistrationPage(){
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(UserForm userForm){
        System.out.println("pass: " + userForm.getPassword());
        System.out.println("confirm pass: " + userForm.getConfirmPassword());
        registrationService.register(userForm);
        return "redirect:/login";
    }
}
