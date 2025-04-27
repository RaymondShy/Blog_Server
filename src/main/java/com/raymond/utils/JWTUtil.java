package com.raymond.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

/*
* JWT工具类
*/
public class JWTUtil {
    // 密钥
    private static final String SECRET = "fnjskngkjnwonfnwffgeg";
    // 过期时间
    private static final Long TOKEN_EXPIRE_TIME = 60 * 60 * 1000l;
    // 签发人
    private static final String ISSUER = "raymond";
    // 保留权限标记的KEY
    private static final String USER_NAME = "raymond";

    // 生成token
    public static String getToken(String username){
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        String token = JWT.create()
                .withIssuer(ISSUER) // 签发人
                .withIssuedAt(new Date()) // 签发时间
                .withExpiresAt(new Date(new Date().getTime() + TOKEN_EXPIRE_TIME)) // 过期时间
                .withClaim(USER_NAME, username) // 保留权限标记
                .sign(algorithm);
        return token;
    }

    // 非法token判断
    public static boolean verifyToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(ISSUER).build();
            verifier.verify(token);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        String token = getToken("raymond");
        System.out.println(token);
    }
}
