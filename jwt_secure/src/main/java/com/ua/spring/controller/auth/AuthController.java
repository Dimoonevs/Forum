package com.ua.spring.controller.auth;

import com.ua.spring.domain.api.auth.authRequest.AuthenticationRequest;
import com.ua.spring.domain.api.auth.authRequest.RegistrationRequest;
import com.ua.spring.domain.response.Response;
import com.ua.spring.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Response> registration(@RequestBody RegistrationRequest request){
        log.info("START point: {}", request);
        ResponseEntity<Response> resp = authService.registration(request);
        log.info("END point: {}", resp);
        return resp;
    }
    @PostMapping("/authenticate")
    public ResponseEntity<Response> authentication(@RequestBody AuthenticationRequest request){
        log.info("START point: {}", request);
        ResponseEntity<Response> resp = authService.authentication(request);
        log.info("END point: {}", resp);
        return resp;
    }
}
