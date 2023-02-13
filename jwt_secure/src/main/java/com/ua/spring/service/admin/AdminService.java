package com.ua.spring.service.admin;

import com.ua.spring.domain.response.Response;
import org.springframework.http.ResponseEntity;

public interface AdminService {
    ResponseEntity<Response> getAllUsers();
    ResponseEntity<Response> enableToggleUserById(Long id);
}
