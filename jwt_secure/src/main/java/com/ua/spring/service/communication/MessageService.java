package com.ua.spring.service.communication;

import com.ua.spring.domain.api.commun.communRequest.MessageReq;
import com.ua.spring.domain.response.Response;
import org.springframework.http.ResponseEntity;

public interface MessageService {

    ResponseEntity<Response> addMessage(MessageReq messageReq);
    ResponseEntity<Response> getAllMessage();
    ResponseEntity<Response> processedMessage(Long id);
}
