package com.example.deploy.controllers;

import com.example.deploy.DTO.user.UserRegistrationDTO;
import com.example.deploy.services.user.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;

@Controller
public class RegistrationController {

    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/registration")
    public String getRegistrationPage(){
        return "registration";
    }

    //Registration with auto login
    @PostMapping("/registration")
    public String registration(UserRegistrationDTO userForm, HttpServletRequest request){
        registrationService.register(userForm);
        try {
            request.login(userForm.getUserName(), userForm.getPassword());
        } catch (ServletException e) {
            LOGGER.error("Error while login ", e);
        }
        return "redirect:/login";
    }
}
