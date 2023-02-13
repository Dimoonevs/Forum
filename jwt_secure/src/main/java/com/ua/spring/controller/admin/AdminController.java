package com.ua.spring.controller.admin;

import com.ua.spring.domain.response.Response;
import com.ua.spring.service.admin.AdminService;
import com.ua.spring.service.communication.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/v1/admin")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
    private final MessageService messageService;

    @GetMapping("/get_users")
    public ResponseEntity<Response> getAllUsers(){
        log.info("START point");
       return adminService.getAllUsers();
    }

    @GetMapping("/user_active")
    public ResponseEntity<Response> enableToggleUserById(@RequestHeader Long id){
        log.info("START point");
        return adminService.enableToggleUserById(id);
    }

    @GetMapping("/get_all_message")
    public ResponseEntity<Response> getAllMessage(){
        return messageService.getAllMessage();
    }

}
