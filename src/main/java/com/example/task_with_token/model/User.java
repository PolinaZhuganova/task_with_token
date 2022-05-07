package com.example.task_with_token.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Класс User
 */
@Entity
@Table(name = "users")
@Data
public class User extends BaseEntity {

	@Column(name = "name")
	private String name;
	@Column(name = "password")
	private String password;

}