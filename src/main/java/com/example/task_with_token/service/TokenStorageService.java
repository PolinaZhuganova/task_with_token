package com.example.task_with_token.service;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Класс TokenStorageService
 */
@Component

public class TokenStorageService {

	@Value("${jwt.token.expired}")
	private long validityInMilliseconds;

	private Map<String, Date> tokenStorage;

	public boolean findToken(String token) {
		if (tokenStorage.containsKey(token)) {
			if (tokenStorage.get(token).getTime() + validityInMilliseconds > new Date().getTime()) {
				tokenStorage.put(token, new Date());
				return true;
			} throw new ;

		}
		return false;
	}

}