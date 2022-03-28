package com.example.deploy.controllers;

import com.example.deploy.services.rate.RateService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class RateController {

    private final RateService rateService;

    @Autowired
    public RateController(RateService rateService) {
        this.rateService = rateService;
    }

    @GetMapping("/saveRate")
    public String saveRate(@RequestParam("post_id") long post_id,
                           @RequestParam("rate") int rate) {
        rateService.save(post_id, rate);
        return "redirect:/feed";
    }

}
