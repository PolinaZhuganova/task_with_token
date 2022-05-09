package com.example.task_with_token;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.*;

/**
 * Класс Main
 */
@SpringBootApplication
@EnableScheduling
public class TokenApplication {

public static void main(String[] args) {
	SpringApplication.run(TokenApplication.class, args);


	}
}