package com.ua.spring.dto.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 10000)
    private String text;

    @JsonIgnore
    @ManyToOne
    @JoinTable(name = "user_comments",
            joinColumns = @JoinColumn(name = "comments_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private User user;


    @JsonIgnore
    @ManyToOne
    @JoinTable(name = "theme_comments",
            joinColumns = @JoinColumn(name = "comments_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "theme_id", referencedColumnName = "id"))
    private Theme themes;
}
