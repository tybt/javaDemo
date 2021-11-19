package com.example.demo.controller;

import com.auth0.jwt.interfaces.Claim;
import com.example.demo.auth.AuthUser;
import com.example.demo.auth.JwtUtil;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    public Boolean checkLogin(String token){
        return JwtUtil.verifyToken(token);
    }



}
