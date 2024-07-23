package com.ilimitech.realstate.administration.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/administrator")
public class AuthController {



    @GetMapping("")
    public String home(){
        return "index";
    }


    // handler method to handle user registration request


    // handler method to handle register user form submit request

}
