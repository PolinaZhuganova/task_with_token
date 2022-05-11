package com.example.task_with_token.service.token;

import com.example.task_with_token.model.User;
import com.example.task_with_token.service.exception.SessionExpiredException;
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

	private Map<String, User> tokenStorage = new HashMap<>();

	public User findToken(String token) throws SessionExpiredException {
		if (tokenStorage.containsKey(token)) {
			User user = tokenStorage.get(token);
			if (user.getExpired().getTime() + validityInMilliseconds > new Date().getTime()) {
				user.setExpired(new Date());

				return user;
			}
			throw new SessionExpiredException("Session expired");

		}
		throw new SessionExpiredException("Token not found");

	}

	@Scheduled(fixedRate = 300000)
	public void tokenStorageCleaner() {
		tokenStorage.entrySet()
			.removeIf(entry -> entry.getValue().getExpired().getTime() + validityInMilliseconds < new Date().getTime());
	}

	public void addToStorage(String token, User user) {
		user.setExpired(new Date());
		tokenStorage.put(token, user);
	}

}