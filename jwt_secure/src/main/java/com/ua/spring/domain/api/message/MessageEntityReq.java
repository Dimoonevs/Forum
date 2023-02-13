package com.ua.spring.domain.api.message;

import com.ua.spring.domain.api.StatusMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageEntityReq {
    private Long id;
    private String message;
    private String email;
    private Boolean isProcessed;
    private StatusMessage statusMessage;
}
