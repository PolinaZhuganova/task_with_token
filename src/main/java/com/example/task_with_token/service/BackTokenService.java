package com.example.task_with_token.service;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Класс TokenService
 */
@Component
@RequiredArgsConstructor
public class BackTokenService implements TokenService {


	private final TokenStorageService tokenStorageService;

	@Value("${jwt.token.secret}")
	private String secret;


	@PostConstruct
	protected void init() {
		secret = Base64.getEncoder().encodeToString(secret.getBytes());
	}


	@Override
	public String generateToken(String name) {
		Claims claims = Jwts.claims().setSubject(name);

		Date now = new Date();
		String token = Jwts.builder()
			.setClaims(claims)
			.setIssuedAt(now)
			.signWith(SignatureAlgorithm.HS256, secret)
			.compact();

		tokenStorageService.addToStorage(token);
		return token;


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