package com.example.authms.config;

import com.example.authms.user.User;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import io.jsonwebtoken.Jwts;
@Service
public class JwtGeneratorImpl implements JwtGeneratorInterface {
    @Value("${jwt.secret}")
    private String secret;

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(this.secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @Value("${app.jwttoken.message}")
    private String message;
    @Override
    public Map<String, String> generateToken(User user) {
        String jwtToken="";
        jwtToken = Jwts.builder().setSubject(user.getUsername()).
                setIssuedAt(new Date()).
                signWith(getSigningKey()).
                compact();
        Map<String, String> jwtTokenGen = new HashMap<>();
        jwtTokenGen.put("token", jwtToken);
        jwtTokenGen.put("message", message);
        return jwtTokenGen;
    }
}