package com.ua.spring.dto.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity(name = "theme")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Theme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 200)
    private String name;

    @Column(length = 1000)
    private String text;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "themes")
    private Set<Comments> comments;
}
