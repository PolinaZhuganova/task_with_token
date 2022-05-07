package com.example.task_with_token.service;

import com.example.task_with_token.model.User;
import com.example.task_with_token.repository.AuthRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Класс AuthService
 */
@Service
@AllArgsConstructor
public class AuthService {
	private AuthRepository authRepository;
	private User user;
	private TokenService tokenService;

	public String generateToken(String name, String password) throws UserNotFoundException {
		user = authRepository.findByName(name);
		if (user == null) {
			throw new UserNotFoundException("User " + name + " not found");
		}

		if (user.getPassword().equals(password)) {
			System.out.println("User found");
			return tokenService.generateToken(user.getName());
		}
		System.out.println("User " + name + " is not found");
		throw new UserNotFoundException("User password for" + name + " is invalid ");


	}
}