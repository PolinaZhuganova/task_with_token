package com.example.task_with_token.repository;

import com.example.task_with_token.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Класс MessageRepository
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}