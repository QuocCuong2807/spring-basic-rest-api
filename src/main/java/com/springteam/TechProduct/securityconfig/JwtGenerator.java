package com.springteam.TechProduct.securityconfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtGenerator {

    //generate token method
    //generate token based on authentication object(username, password)
    public String generateToken(Authentication auth){

        //get username, current date and expire date
        String userName = auth.getName();
        Date currentDate = new Date();
        Date jwtExpireDate = new Date(currentDate.getTime() + JwtConstant.JWT_EXPIRATION);

        //Build token based on:
        //1. username from authentication object
        //2. jwt start date and expire date
        //3. name of create sign algorithm and secret key
        String token = Jwts.builder()
                .setSubject(userName)
                .setIssuedAt(new Date())
                .setExpiration(jwtExpireDate)
                .signWith(SignatureAlgorithm.HS512, JwtConstant.JWT_SECRET)
                .compact();
        return token;
    }

    //get username from token method
    public String getUserNameFromToken(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(JwtConstant.JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(JwtConstant.JWT_SECRET).parseClaimsJws(token);
            return true;
        }catch (JwtException e){

            throw new JwtException("invalid token - could you reLogin");
        }
    }
}
