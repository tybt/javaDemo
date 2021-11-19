package com.example.demo.auth;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JwtUtil {


    private static final String SECRET = "my_secret";


    private static final long EXPIRATION = 1800L;//单位为秒


    public static String createToken(AuthUser authUser) {
        //过期时间
        Date expireDate = new Date(System.currentTimeMillis() + EXPIRATION * 1000);
//        Map<String, Object> map = new HashMap();
//        map.put("alg", "HS256");
//        map.put("typ", "JWT");
        String token = JWT.create()
//                .withHeader(map)// 添加头部
                //可以将基本信息放到claims中
                .withClaim("id", authUser.getId())
                .withExpiresAt(AuthConfig.timeout) //超时设置,设置过期的日期
                .withIssuedAt(new Date()) //签发时间
                .sign(Algorithm.HMAC256(SECRET)); //SECRET加密
        return token;
    }

    /**
     * 校验token并解析token
     */
    public static Boolean verifyToken(String token) {
        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            jwt = verifier.verify(token);
        } catch (Exception e) {
            log.error(String.valueOf(e));
            return false;
        }
        Map<String, Claim> claims = jwt.getClaims();
        String id=claims.get("id").asString();
        return id != null;

    }

}
