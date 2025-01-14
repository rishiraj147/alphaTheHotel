package com.alphadev.AlphaHotel.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
	 private final String SECRET_KEY = "rzzzshhhhiiithsisdjbfshdfjshfbshjfbjhebfuhebfbsdchbsuhfbuheqbfhasncdisbjhsfahsbdfvkdsbfvbdvbdsjfgwdfb";
	 private final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour
	 
		public String getUsernameFromToken(String token)
		{
			Claims claims =  getClaimsFromToken(token);
			return claims.getSubject();
		}
		
		public Claims getClaimsFromToken(String token)
		{
			Claims claims = Jwts.parser().setSigningKey(SECRET_KEY.getBytes())
					.build().parseClaimsJws(token).getBody();
			return claims;
		}
		
		public Boolean isTokenExpired(String token)
		{
			Claims claims =  getClaimsFromToken(token);
			Date expDate = claims.getExpiration();
			return expDate.before(new Date());
		}
	 
		public String generateToken(UserDetails userDetails) {
		
		Map<String,Object> claims = new HashMap<>();
		
		return Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername())
		.setIssuedAt(new Date(System.currentTimeMillis()))
		.setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
		.signWith(new SecretKeySpec(SECRET_KEY.getBytes(),SignatureAlgorithm.HS512.getJcaName()),SignatureAlgorithm.HS512)
		.compact();
	}

	 
	 
}
