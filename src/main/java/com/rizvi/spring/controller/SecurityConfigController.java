package com.rizvi.spring.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityConfigController {

    @RequestMapping("/home")
    public String index(){
        return "Greeting from Spring Boot!!!";
    }
}
