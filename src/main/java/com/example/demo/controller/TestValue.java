package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testValue")
public class TestValue {
    @Value("${test.name}")
    String a;
    @GetMapping("/test")
    public String test(){
        return this.a;
    }
}
