package com.ua.spring.controller.communication;

import com.ua.spring.domain.api.commun.communRequest.MessageReq;
import com.ua.spring.domain.response.Response;
import com.ua.spring.service.communication.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/message")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;
    @PostMapping("add")
    public ResponseEntity<Response> addMessage(@RequestBody MessageReq req){
        return messageService.addMessage(req);
    }

    @GetMapping("get")
    public ResponseEntity<Response> getAllMessage(){
        return messageService.getAllMessage();
    }

    @GetMapping("done")
    public ResponseEntity<Response> processedMessage(@RequestHeader Long id){
        return messageService.processedMessage(id);
    }

}
