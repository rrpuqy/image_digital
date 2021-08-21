package com.example.digitalimage.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.digitalimage.exception.ExceptionEnum;
import com.example.digitalimage.exception.MyException;
import com.example.digitalimage.model.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service("TokenService")
public class TokenService {

    @Value("${token.privateKey}")
    private String privateKey;

    public String getToken(Long userId) {
        Date start = new Date();
        long currentTime = System.currentTimeMillis() + 60* 60 * 1000 * 24;
        Date end = new Date(currentTime);
        String token= JWT.create().withClaim("userId",userId)
                .withIssuedAt(start)
                .withExpiresAt(end)// 将 user id 保存到 token 里面
                .sign(Algorithm.HMAC256(privateKey));// 以 password 作为 token 的密钥
        return token;
    }

    public Map<String, Object> parseToken(String token) throws Exception {
        HashMap<String, Object> map = new HashMap<>();

        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(privateKey))
                    .build();
            DecodedJWT decodedJWT = verifier.verify(token);
            Claim userId = decodedJWT.getClaim("userId");
            Long tmp = Long.valueOf(userId.asString());
            Claim timeStamp = decodedJWT.getClaim("timeStamp");
            map.put("userId", tmp);
            map.put("timeStamp", timeStamp);
            return map;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw  new MyException(ExceptionEnum.JWT_ERR);
        } catch (JWTVerificationException e) {
            throw  new MyException(ExceptionEnum.JWT_ERR);
        }


    }
}
