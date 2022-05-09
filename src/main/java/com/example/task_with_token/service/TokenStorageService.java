package com.example.task_with_token.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Класс TokenStorageService
 */
@Component

public class TokenStorageService {

	@Value("${jwt.token.expired}")
	private long validityInMilliseconds;

	private Map<String, Date> tokenStorage = new HashMap<>();

	public boolean findToken(String token) throws SessionExpiredException {
		if (tokenStorage.containsKey(token)) {
			if (tokenStorage.get(token).getTime() + validityInMilliseconds > new Date().getTime()) {
				tokenStorage.put(token, new Date());
				return true;
			}
			throw new SessionExpiredException("Session expired");

		}
		return false;
	}

	@Scheduled(fixedRate = 300000)
	public void tokenStorageCleaner() {
		tokenStorage.entrySet()
			.removeIf(entry -> entry.getValue().getTime() + validityInMilliseconds < new Date().getTime());
	}

	public void addToStorage(String token) {
		tokenStorage.put(token, new Date());
	}

}