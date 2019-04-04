package com.sxx.sivir.web.security.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.sxx.sivir.core.dal.domain.User;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenHelper {

    //生成Token
    public String getToken(User user) {
        String token = "";
        token = JWT.create().withAudience(user.getUserId().toString())
                //过期时间1小时
                .withExpiresAt(new Date(System.currentTimeMillis() + 6000))
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
}
