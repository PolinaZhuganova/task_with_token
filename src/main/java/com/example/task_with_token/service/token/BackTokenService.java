package com.example.task_with_token.service.token;

import com.example.task_with_token.model.User;
import com.example.task_with_token.service.exception.SessionExpiredException;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
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
	public String generateToken(User user) {
		Claims claims = Jwts.claims().setSubject(user.getName());

		Date now = new Date();
		String token = Jwts.builder()
			.setClaims(claims)
			.setIssuedAt(now)
			.signWith(SignatureAlgorithm.HS256, secret)
			.compact();

		tokenStorageService.addToStorage(token, user);
		return token;


	}

	public String resolveToken(String bearerToken) {

		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}


	@Override
	public User findToken(String bearerToken) throws SessionExpiredException {
		String token = resolveToken(bearerToken);
		return tokenStorageService.findToken(token);
	}

	@Override
	public void removeExpiredTokens() {

	}
}