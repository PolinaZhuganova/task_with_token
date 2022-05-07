package com.example.task_with_token.repository;

import com.example.task_with_token.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Класс AuthRepository
 */
@Repository
public interface AuthRepository extends JpaRepository<User, Long> {

	User findByName(String name);

}