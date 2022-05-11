package com.example.task_with_token.service.message;

import com.example.task_with_token.dto.MessageRequestDto;
import com.example.task_with_token.model.User;

/**
 * Класс MessageService
 */
public interface MessageService {

	void saveMessage(MessageRequestDto message, User user);
	void getMessage();
}