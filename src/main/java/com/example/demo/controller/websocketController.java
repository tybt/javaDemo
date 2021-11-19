package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/websocket")
public class websocketController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @GetMapping("/test")
    public String test(){
        simpMessagingTemplate.convertAndSend("/getMsg/test/info","我是你爸爸");
        return "123456";
    }


}
