package com.example.demo.controller;

import com.example.demo.annotations.TestAnonations;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/annotation")
@Api(tags = "注解控制")
public class AnnotationController {
    @GetMapping("/setAnno")
    @TestAnonations
    @ApiOperation("测试")
    public void setAnno(String name){
        System.out.println("靠 怎么不执行");

    }
}
