package com.ua.spring.dto.entity;

import com.ua.spring.domain.api.StatusMessage;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 10000)
    private String message;
    private String email;
    private Boolean isProcessed;
    private Date localDateTime;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private StatusMessage statusMessage;





}
