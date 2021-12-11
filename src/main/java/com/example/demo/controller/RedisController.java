package com.example.demo.controller;


import com.example.demo.redis.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/redis")
@Slf4j
public class RedisController {
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private RedisTemplate<String, User> redisTemplate;



    @GetMapping("/setRedis")
    public void setRedis(String name){
        log.info("woshinibaba");
        log.debug(name);
        User user=new User();
        user.setId("123");
        user.setName("baba");
        user.setPhone(110);
        redisTemplate.opsForHash().put("user",1,user);

//        return  redisTemplate.opsForHash().get("user",1);

    }
    @GetMapping("/getRedis")
    public String getRedis(){
        User user= redisTemplate.opsForValue().get("baba");
        assert user != null;
        System.out.println(user.getName());
        return  user.getId();

    }
}
