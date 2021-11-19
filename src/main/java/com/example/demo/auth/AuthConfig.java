package com.example.demo.auth;

import lombok.Data;

import java.util.Date;

@Data
public class AuthConfig {

     // token名称 (同时也是cookie名称)
    static String tokenName= "satoken";

    // token有效期，单位s 默认30天, -1代表永不过期
    static Date timeout=new Date(System.currentTimeMillis() + 2592000);

    // token临时有效期 (指定时间内无操作就视为token过期) 单位: 秒
    static Integer activityTimeout= -1;

    // 是否允许同一账号并发登录 (为true时允许一起登录, 为false时新登录挤掉旧登录)
    static Boolean isConcurrent= true;

    // 在多人登录同一账号时，是否共用一个token (为true时所有登录共用一个token, 为false时每次登录新建一个token)
    static Boolean isShare=false;

    // token风格
    static String tokenStyle= "uuid";

    // 是否输出操作日志
    static Boolean isLog= false;
}
