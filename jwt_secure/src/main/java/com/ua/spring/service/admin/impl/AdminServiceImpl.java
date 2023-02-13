package com.ua.spring.service.admin.impl;

import com.ua.spring.domain.response.Response;
import com.ua.spring.domain.response.SuccessResponse;
import com.ua.spring.dto.entity.User;
import com.ua.spring.dto.repository.UserRepo;
import com.ua.spring.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final UserRepo userRepo;

    @Override
    public ResponseEntity<Response> getAllUsers() {

        List<User> users = userRepo.findAll().stream().toList();

        return new ResponseEntity<>(SuccessResponse.builder().data(users).build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> enableToggleUserById(Long id) {
        User user = userRepo.findById(id).get();

        user.setIsActive(!user.getIsActive());
        userRepo.save(user);

        return new ResponseEntity<>(SuccessResponse.builder().data("Success blocked!").build(), HttpStatus.OK);
    }
}
