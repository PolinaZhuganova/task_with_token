package com.example.task_with_token.controller;

import com.example.task_with_token.dto.*;
import com.example.task_with_token.model.User;
import com.example.task_with_token.service.*;
import com.example.task_with_token.service.exception.*;
import com.example.task_with_token.service.message.MessageService;
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
	private MessageService messageService;

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

	@PostMapping(value = "/addMessage", produces = "application/json")
	public ResponseEntity addMessage(@RequestHeader HttpHeaders headers, @RequestBody MessageRequestDto messageDto){
		String bearerToken = headers.getFirst("Authorization");
		try {
			User user = authService.findToken(bearerToken);
			if(user != null ){
				messageService.saveMessage(messageDto, user);
			}
		} catch (SessionExpiredException e) {
			new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return null;
	}
}