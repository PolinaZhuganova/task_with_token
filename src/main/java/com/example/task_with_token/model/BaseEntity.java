package com.example.task_with_token.model;

import lombok.Data;

import javax.persistence.*;


@MappedSuperclass
@Data
public class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


//
//    @Enumerated(EnumType.STRING)
//    @Column(name = "status")
//    private Status status;
}
