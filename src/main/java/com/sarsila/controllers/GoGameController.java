package com.sarsila.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class GoGameController {

    @RequestMapping("/")
    public String index() {
        return "Greetings asasas Spring Boot!";
    }

}

