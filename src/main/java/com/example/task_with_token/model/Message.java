package com.example.task_with_token.model;

import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.persistence.Id;
import java.util.Date;

/**
 * Класс Message
 */

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "messages")
@Getter
@Setter
@NoArgsConstructor
public class Message  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "message")
	private String message;

	@CreatedDate
	private Date created;

	@CreatedBy
	@ManyToOne
	@JoinColumn(name = "name_id")
	private User user;
}