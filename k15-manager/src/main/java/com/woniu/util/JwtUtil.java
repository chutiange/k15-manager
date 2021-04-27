package com.woniu.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.util.Date;

/**
 * @Author Administrator
 * @Date 2021/4/16 12:13
 */

public class JwtUtil {
    /**
     * 过期时间50分钟
     */
    private static final long EXPIRE_TIME = 5 * 60 * 10000;
    /**
     * jwt 密钥
     */
    private static final String SECRET = "xiaokongming";

    /*
    生成签名  50分钟过期
     */
    public static String createSign(String userName) throws Exception {
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            return JWT.create()
                    // 将 user id 保存到 token 里面
                    .withAudience(userName)
                    // 50分钟后token过期
                    .withExpiresAt(date)
                    //.withClaim()
                    //.withSubject(userName)
                    // token 的密钥
                    .sign(algorithm);
        }catch(Exception ex){
            ex.printStackTrace();
            throw new Exception("签名错误");
        }
    }
    /**
     * 根据token获取username
     * @param token
     * @return
     */
    public static String getAudience(String token) {
        try {
            String audience = JWT.decode(token).getAudience().get(0);
            return audience;
        } catch (JWTDecodeException e) {
            throw new JWTDecodeException("生成的token 异常");
        }
    }
    /**
     * 校验token
     * @param token
     * @return
     */
    public static boolean checkSign(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    // .withClaim("username", username)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("token 无效，请重新获取");
        }
    }

}
