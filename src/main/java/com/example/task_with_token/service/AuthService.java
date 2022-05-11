package com.example.task_with_token.service;

import com.example.task_with_token.model.User;
import com.example.task_with_token.repository.AuthRepository;
import com.example.task_with_token.service.exception.*;
import com.example.task_with_token.service.token.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Класс AuthService
 */
@Service
@AllArgsConstructor
public class AuthService {
	private AuthRepository authRepository;
	private TokenService tokenService;

	public User findByName(String name) throws UserNotFoundException {
		User user = authRepository.findByName(name);
		if (user == null) {
			throw new UserNotFoundException("User " + name + " not found");
		}
		return user;
	}

	public String generateToken(String name, String password) throws UserNotFoundException {
		User user = findByName(name);

		if (user.getPassword().equals(password)) {
			System.out.println("User found");
			return tokenService.generateToken(user);
		}
		System.out.println("User " + name + " is not found");
		throw new UserNotFoundException("User password for" + name + " is invalid ");
	}

	public User findToken(String bearerToken) throws SessionExpiredException {
		return tokenService.findToken(bearerToken);
	}
}