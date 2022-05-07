package com.example.task_with_token.service;

/**
 * Класс TokenService
 */
public interface TokenService {
	String generateToken(String name);
	boolean findToken();
	void removeExpiredTokens();
}