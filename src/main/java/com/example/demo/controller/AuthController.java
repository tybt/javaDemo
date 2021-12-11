package com.example.demo.controller;

import com.example.demo.annotations.CheckLogin;
import com.example.demo.auth.AuthUser;
import com.example.demo.auth.JwtUtil;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/getToken")
    public String getToken(String name,String id){
        AuthUser authUser=new AuthUser();
        authUser.setId(id);
        authUser.setName(name);
        return JwtUtil.createToken(authUser);

    }


    @GetMapping("/checkLogin")

    public Boolean checkLogin(String token) {
        return JwtUtil.verifyToken(token);
    }

    @GetMapping("/logout")
    public void logout(String token){
         JwtUtil.logout(token);
    }

    @GetMapping("/isLogin")
    @CheckLogin
    public void isLogin( @RequestHeader(value="token") String headerStr){
        System.out.println("哈哈哈测试而已");
    }





}
