package com.example.task_with_token.controller;

import com.example.task_with_token.dto.TokenDto;
import com.example.task_with_token.model.User;
import com.example.task_with_token.service.*;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

/**
 * Класс NameController
 */
@RestController
@AllArgsConstructor
public class NameController {

	private AuthService authService;

	@PostMapping(value = "/auth", produces = "application/json")
	public ResponseEntity makeToken(@RequestBody User user) {
		try {
			String token = authService.generateToken(user.getName(), user.getPassword());
			System.out.println(user.getName() + user.getPassword());
			return new ResponseEntity(new TokenDto(token), HttpStatus.OK);

		} catch (UserNotFoundException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}


}