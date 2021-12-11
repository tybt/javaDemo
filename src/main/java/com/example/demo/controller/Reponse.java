package com.example.demo.controller;

import com.example.demo.response.BusinessException;
import com.example.demo.response.ResultEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/response")
public class Reponse {
    @GetMapping("/success")
    public void success(){
        throw new RuntimeException("测试测试");
//        throw new BusinessException(ResultEnum.SYSTEM_ERROR);
    }
}
