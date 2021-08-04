package com.example.digitalimage.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.digitalimage.model.entity.User;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("TokenService")
public class TokenService {
    public String getToken(User user) {
        Date start = new Date();
        long currentTime = System.currentTimeMillis() + 60* 60 * 1000 * 24;
        Date end = new Date(currentTime);
        String token="";
        token= JWT.create().withClaim("id",user.getUserId())
                .withIssuedAt(start)
                .withExpiresAt(end)// 将 user id 保存到 token 里面
                .sign(Algorithm.HMAC256(user.getPassword()));// 以 password 作为 token 的密钥
        return token;
    }
}
