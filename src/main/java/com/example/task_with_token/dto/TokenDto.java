package com.example.task_with_token.dto;

/**
 * Класс TokenDto
 */

public class TokenDto {
	private String token;

	public TokenDto(String token) {
		this.token = token;
	}

	/**
	 * Возвращает
	 *
	 * @return
	 */
	public String getToken() {
		return token;
	}
}
