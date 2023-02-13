package com.ua.spring.domain.response.exception;

import com.ua.spring.domain.api.Code;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class CommonException extends RuntimeException{
    private Code code;
    private String message;
    private HttpStatus httpStatus;
}
