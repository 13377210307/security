package com.jwt.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtils {

    private final String SECRET = "tokenSecret";

    private final Long EXPIRATION = 1000L * 3600 * 24;  // 过期时间一天


    /**
     * 生成token
     */
    public String generateToken(UserDetails userDetails) {
        Map<String,Object> claims = new HashMap<>();
        claims.put("sub",userDetails.getUsername());
        claims.put("createTime", new Date());
        return this.generateToken(claims);
    }

    /**
     * 从令牌中获取用户名
     */
    public String getUsernameByToken(String token) {
        String username;
        Claims claims = getClaimByToken(token);
        username = claims.getSubject();
        return username;
    }

    /**
     * 判断令牌是否过期
     */
    public Boolean isTokenExpired(String token) {
        Claims claims = this.getClaimByToken(token);
        Date expiration = claims.getExpiration();
        return expiration.before(new Date());
    }

    /**
     * 刷新令牌
     */
    public String refreshToken(String token) {
        Claims claims = this.getClaimByToken(token);
        claims.put("createTime",new Date());  //重新设置token生成时间
        return generateToken(claims);
    }

    /**
     * 验证令牌
     */
    public Boolean validToken(String token,UserDetails userDetails) {
        String username = getUsernameByToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * 获取请求头中的真正token值
     */
    public String realToken(String head) {
        return head.replace("Bearer ", "");
    }

    /**
     * 生成token
     */
    private String generateToken(Map<String,Object> claims) {
        Date expirationDate = new Date(System.currentTimeMillis() + EXPIRATION);
        return Jwts.builder().setClaims(claims)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256,SECRET)
                .compact();
    }


    /**
     * 从令牌中获取数据声明
     */
    private Claims getClaimByToken(String token) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
    }

}
