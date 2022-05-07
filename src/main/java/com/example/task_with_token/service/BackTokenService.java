package com.example.task_with_token.service;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Класс TokenService
 */
@Component
public class BackTokenService implements TokenService {


	@Value("${jwt.token.secret}")
	private String secret;


	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	@PostConstruct
	protected void init() {
		secret = Base64.getEncoder().encodeToString(secret.getBytes());
	}


	@Override
	public String generateToken(String name) {
		Claims claims = Jwts.claims().setSubject(name);

		Date now = new Date();
//		Date validity = new Date(now.getTime() + validityInMilliseconds);

		return Jwts.builder()
			.setClaims(claims)
			.setIssuedAt(now)
//			.setExpiration(validity)
			.signWith(SignatureAlgorithm.HS256, secret)
			.compact();
	}

	public String resolveToken(HttpServletRequest req) {
		String bearerToken = req.getHeader("Authorization");
		if (bearerToken != null && bearerToken.startsWith("Bearer_")) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}


	@Override
	public boolean findToken() {
		return false;
	}

	@Override
	public void removeExpiredTokens() {

	}
}