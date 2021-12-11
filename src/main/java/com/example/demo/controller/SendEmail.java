package com.example.demo.controller;

import com.example.demo.email.EmailDTO;
import com.example.demo.email.EmailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/sendEmail")
public class SendEmail {
    @Resource
    EmailService emailService;
    @PostMapping("/sendText")
    public void sendText(@RequestBody EmailDTO emailDTO){
        emailService.sendTextEmail(emailDTO);

    }
}
