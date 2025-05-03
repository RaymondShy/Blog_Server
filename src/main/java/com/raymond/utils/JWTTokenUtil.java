package com.raymond.utils;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;

/*
* JWT工具类
*/
@Component
public class JWTTokenUtil {
    // 密钥
    private static final String SECRET = "fnjskngkjnwonfnwffgeg";
    // 过期时间
    private static final Long TOKEN_EXPIRE_TIME = 60 * 60 * 1000l;
    // 签发人
    private static final String ISSUER = "raymond";
    // 保留权限标记的KEY
    private static final String USER_NAME = "raymond";

    // 生成token
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRE_TIME))
//                .setIssuer(ISSUER)
                .signWith(SignatureAlgorithm.HS256,SECRET)
                .compact();
    }

    // 解析jwt token
    public Claims parseToken(String token) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
    }

    // 从token中提取用户名
    public String getUsername(String token) {
        return parseToken(token).getSubject();
    }

    // 检查 Token 是否过期
    public boolean isTokenExpired(String token) {
        return parseToken(token).getExpiration().before(new Date());
    }

    // 验证 Token 的有效性
    public boolean validateToken(String token, String username) {
        return username.equals(getUsername(token)) && !isTokenExpired(token);
    }

}
