package com.example.demo.controller;

import com.example.demo.kafka.KafkaSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/kafka")
@Slf4j
public class KafkaController {
    @Resource
    KafkaSender kafkaSender;

    @GetMapping("/sender")
    public void sender(String name){
        log.debug(name);
        kafkaSender.send(name);

    }
}
