package com.example.task_with_token.model;

import lombok.Data;

import javax.persistence.*;
import java.util.*;

/**
 * Класс User
 */
@Entity
@Table(name = "users")
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "password")
	private String password;

	@Transient
	private Date expired;


}