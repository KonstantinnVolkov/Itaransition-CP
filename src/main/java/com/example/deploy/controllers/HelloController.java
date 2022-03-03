package com.example.deploy.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Azure Spring Cloud!";
    }

    @RequestMapping("/home")
    public String home() {
        return "home";
    }
}
