package com.ua.spring.dto.repository;

import com.ua.spring.dto.entity.Comments;
import com.ua.spring.dto.entity.Theme;
import com.ua.spring.dto.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentsRepo extends JpaRepository<Comments, Long> {
    List<Comments> findByUser(User user);

    List<Comments> findByThemes(Theme theme);
}
