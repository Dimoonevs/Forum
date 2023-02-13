package com.ua.spring.service.auth.impl;

import com.ua.spring.domain.api.Code;
import com.ua.spring.domain.api.Role;
import com.ua.spring.domain.api.UserDetailsImpl;
import com.ua.spring.domain.api.auth.authRequest.AuthenticationRequest;
import com.ua.spring.domain.api.auth.authRequest.RegistrationRequest;
import com.ua.spring.domain.api.auth.authResponse.AuthenticationResponse;
import com.ua.spring.domain.response.Response;
import com.ua.spring.domain.response.SuccessResponse;
import com.ua.spring.domain.response.exception.CommonException;
import com.ua.spring.dto.entity.User;
import com.ua.spring.dto.repository.UserRepo;
import com.ua.spring.service.auth.AuthService;
import com.ua.spring.service.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public ResponseEntity<Response> registration(RegistrationRequest request) {
        if (userRepo.existsByUsername(request.getUsername())){
            throw CommonException.builder()
                    .code(Code.NICKNAME_BUSY)
                    .message("Пользователь с данным никнеймом существует!")
                    .httpStatus(HttpStatus.BAD_REQUEST).build();
        }
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .isActive(true).build();
        userRepo.save(user);
        UserDetailsImpl userDetails = UserDetailsImpl.builder().user(user).build();
        String jwtToken = jwtService.generateToken(userDetails);
        return new ResponseEntity<>(SuccessResponse.builder().data(AuthenticationResponse.builder().token(jwtToken).role(user.getRole()).build()).build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> authentication(AuthenticationRequest request) {
        if (!userRepo.existsByUsername(request.getUsername())){
            throw CommonException.builder()
                    .code(Code.USER_NOT_FOUND)
                    .message("Пользователь с данным никнеймом не существует")
                    .httpStatus(HttpStatus.BAD_REQUEST).build();
        }
        User user = userRepo.findByUsername(request.getUsername());
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw CommonException.builder()
                    .code(Code.PASSWORD_ERROR)
                    .message("Пароли не совподают")
                    .httpStatus(HttpStatus.BAD_REQUEST).build();
        }
        if(!user.getIsActive()){
            throw CommonException.builder()
                    .code(Code.USER_BLOCKED)
                    .message("Пользовател заблокирован, обратитесь к администратору")
                    .httpStatus(HttpStatus.BAD_REQUEST).build();
        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        UserDetailsImpl userDetails = UserDetailsImpl.builder().user(user).build();
        String jwtToken = jwtService.generateToken(userDetails);
        return new ResponseEntity<>(SuccessResponse.builder().data(AuthenticationResponse.builder().token(jwtToken).role(user.getRole()).build()).build(), HttpStatus.OK);
    }

}
