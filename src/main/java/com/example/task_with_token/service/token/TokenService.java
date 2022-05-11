package com.example.task_with_token.service.token;

import com.example.task_with_token.model.User;
import com.example.task_with_token.service.exception.SessionExpiredException;

/**
 * Класс TokenService
 */
public interface TokenService {
	String generateToken(User name);
	User findToken(String bearerToken) throws SessionExpiredException;
	void removeExpiredTokens();
}