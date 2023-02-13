package com.ua.spring.dto.repository;

import com.ua.spring.dto.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepo extends JpaRepository<Message, Long> {
    boolean existsByEmail(String email);

    Message findByEmail(String email);

}
