package com.example.demo.aspects;

import com.example.demo.auth.JwtUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import utils.ResultCode;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Aspect
@Component
public class CheckLoginAspect {
    @Resource
    HttpServletResponse httpServletResponse;
    @Resource
    HttpServletRequest httpServletRequest;
    @Resource
    RedisTemplate redisTemplate;

    @Around("@annotation(com.example.demo.annotations.CheckLogin)")
    public Boolean checkLogin(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        String token=httpServletRequest.getHeader("token");
        System.out.println(token);
        String id = JwtUtil.getUserIdByToken(token);
        String redisToken=(String) redisTemplate.opsForHash().get("auth_"+id,"token");

        return true;


    }
}
