package com.ua.spring.service.communication;

import com.ua.spring.domain.response.Response;
import org.springframework.http.ResponseEntity;

public interface SearchService {
    ResponseEntity<Response> getCommentsByUser(String jwt);
}
