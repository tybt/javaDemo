package com.example.demo.auth;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

@Slf4j
@Component
public class JwtUtil {


    private static final String SECRET = "my_secret";

    private static RedisTemplate redisTemplate;


    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate){
        JwtUtil.redisTemplate = redisTemplate;
    }


    public static String createToken(AuthUser authUser) {
        Date currentDate=new Date();

        String token = JWT.create()
                .withClaim("id", authUser.getId())
                .withExpiresAt(AuthConfig.timeout) //超时设置,设置过期的日期
                .withIssuedAt(currentDate) //签发时间
                .sign(Algorithm.HMAC256(SECRET)); //SECRET加密
        String redisKey="auth_"+authUser.getId();
        redisTemplate.opsForHash().put(redisKey,"createTime",currentDate);
        redisTemplate.opsForHash().put(redisKey,"token",token);
        redisTemplate.opsForHash().put(redisKey,"id",authUser.getId());
        return token;
    }

    public static Boolean verifyToken(String token) {
        String id=getUserIdByToken(token);
        return id != null;

    }

    public static String getUserIdByToken(String token) {
        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            jwt = verifier.verify(token);
        } catch (Exception e) {
            log.error(String.valueOf(e));
            return null;
        }
        Map<String, Claim> claims = jwt.getClaims();
        return claims.get("id").asString();

    }



    public static void logout(String token){
        String id=getUserIdByToken(token);
        redisTemplate.opsForHash().delete("auth_"+id);
    }

}
