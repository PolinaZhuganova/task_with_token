package com.example.task_with_token.service.message;

import com.example.task_with_token.dto.MessageRequestDto;
import com.example.task_with_token.model.*;
import com.example.task_with_token.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Класс BackMessageService
 */
@Service
@RequiredArgsConstructor
public class BackMessageService implements MessageService {
	private final MessageRepository messageRepository;

	@Override
	public void saveMessage(MessageRequestDto messageRequestDto, User user) {
		Message message = new Message();
		message.setMessage(messageRequestDto.getMessage());
		message.setCreated(new Date());
		message.setUser(user);
		messageRepository.save(message);
	}

	@Override
	public void getMessage() {

	}
}