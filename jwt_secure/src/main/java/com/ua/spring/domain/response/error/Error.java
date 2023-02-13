package com.ua.spring.domain.response.error;

import com.ua.spring.domain.api.Code;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Error {
    private Code code;
    private String message;
}
