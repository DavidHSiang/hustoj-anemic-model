package com.zjc.hustoj.auth.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

/**
 * @author David Hsiang
 * @date 2021/04/06/10:48 下午
 */
@Component
public class TokenProvider implements InitializingBean {

    private final Logger log = LoggerFactory.getLogger(TokenProvider.class);

    private static final String VERIFY_CODE_KEY = "verifyCode";
    private static final String USERID_KEY = "userId";
    private final String base64Secret = "ZmQ0ZGI5NjQ0MDQwY2I4MjMxY2Y3ZmI3MjdhN2ZmMjNhODViOTg1ZGE0NTBjMGM4NDA5NzYxMjdjOWMwYWRmZTBlZjlhNGY3ZTg4Y2U3YTE1ODVkZDU5Y2Y3OGYwZWE1NzUzNWQ2YjFjZDc0NGMxZWU2MmQ3MjY1NzJmNTE0MzI=";
    private final long userIdTokenValidityInMilliseconds = 86400000;
    private final long verifyCodeTokenValidityInMilliseconds = 60000;
    private final String issuer = "zjc";

    private Key key;


    @Override
    public void afterPropertiesSet() {
        byte[] keyBytes = Decoders.BASE64.decode(base64Secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String createVerifyCodeToken(String verifyCode) {
        Date validity = new Date(System.currentTimeMillis() + this.verifyCodeTokenValidityInMilliseconds);
        return Jwts.builder()
                .setIssuer(issuer)
                .claim(VERIFY_CODE_KEY, verifyCode)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(validity)
                .compact();
    }

    public String createUserIdToken(String userId) {
        Date validity = new Date(System.currentTimeMillis() + this.userIdTokenValidityInMilliseconds);
        return Jwts.builder()
                .setIssuer(issuer)
                .claim(USERID_KEY, userId)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(validity)
                .compact();
    }

    public String getVerifyCode(String token) {
        return this.getByClaimKey(VERIFY_CODE_KEY, token);
    }

    public String getUserId(String token) {
        return this.getByClaimKey(USERID_KEY, token);
    }

    public String getByClaimKey(String claimKey, String token){
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token)
                    .getBody();
            if (currentDate().after(claims.getExpiration())){
                return "";
            }
            return claims.get(claimKey).toString();
        } catch (Exception e){
            log.error(e.getMessage(), e);
            return "";
        }
    }

    private Date currentDate(){
        return new Date();
    }
}