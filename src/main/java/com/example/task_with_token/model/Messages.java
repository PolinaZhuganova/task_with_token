package com.example.task_with_token.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

/**
 * Класс Messages
 */
@Entity
@Table(name = "messages")
@Data
public class Messages extends BaseEntity {

	@Column(name = "message")
	private String message;

	@CreatedDate
	@Column(name = "created_at")
	private Date created;

	@Column(name = "name_id")
	private String nameId;
}