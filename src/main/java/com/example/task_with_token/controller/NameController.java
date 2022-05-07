package com.example.task_with_token.controller;

import com.example.task_with_token.service.*;
import org.springframework.web.bind.annotation.*;

/**
 * Класс NameController
 */
@RestController
public class NameController {

	private AuthService authService;

	@PostMapping("/auth")
	public String makeToken(@RequestParam String name, @RequestParam String password) {
		try {
			authService.generateToken(name, password);
		} catch (UserNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println(name + password);
		return null;
	}


}