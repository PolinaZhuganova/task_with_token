package com.example.task_with_token.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Класс MessageDto
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageRequestDto {

	private String name;
	private String message;



}