package com.ua.spring.service.auth;

import com.ua.spring.domain.api.auth.authRequest.AuthenticationRequest;
import com.ua.spring.domain.api.auth.authRequest.RegistrationRequest;
import com.ua.spring.domain.response.Response;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<Response> registration(RegistrationRequest request);

    ResponseEntity<Response> authentication(AuthenticationRequest request);
}
