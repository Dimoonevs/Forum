package com.ua.spring.domain.response.exception;

import com.ua.spring.domain.api.Code;
import com.ua.spring.domain.response.error.Error;
import com.ua.spring.domain.response.error.ErrorResponse;
import io.jsonwebtoken.MalformedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.security.SignatureException;

@Slf4j
@ControllerAdvice
public class ForumServiceErrorHandler {

    @ExceptionHandler(CommonException.class)
    public ResponseEntity<ErrorResponse> handleCommonException(CommonException commonException){
        log.error("common error: {}", commonException.toString());
        return new ResponseEntity<>(ErrorResponse.builder().error(Error.builder()
                .code(commonException.getCode())
                .message(commonException.getMessage())
                .build()).build(), commonException.getHttpStatus());
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<ErrorResponse> handleSignatureException(SignatureException signatureException){
        log.error("signature error: {}", signatureException.toString());
        return new ResponseEntity<>(ErrorResponse.builder()
                .error(Error.builder()
                        .message("Ошибка сигнатуры токена")
                        .code(Code.TOKEN_VALIDATION_ERROR)
                        .build()).build(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<ErrorResponse> handleMalformedJwtException(MalformedJwtException exception){
        log.error("Malformed jwt error: {}", exception.toString());
        return new ResponseEntity<>(ErrorResponse.builder()
                .error(Error.builder()
                        .code(Code.TOKEN_VALIDATION_ERROR)
                        .message("Ошибка сигнатуры токена")
                        .build()).build(), HttpStatus.BAD_REQUEST);
    }
}
