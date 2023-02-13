package com.ua.spring.dto.repository;

import com.ua.spring.dto.entity.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ThemeRepo extends JpaRepository<Theme, Long> {
    Optional<Theme> findById(Long id);

    @Query("select t from theme t" +
            " where t.name like concat('%',lower(?1), '%' ) ")
    List<Theme> findByName(String name);
}
