package com.ua.spring.service.communication.impl;

import com.ua.spring.domain.api.Code;
import com.ua.spring.domain.api.StatusMessage;
import com.ua.spring.domain.api.commun.communRequest.MessageReq;
import com.ua.spring.domain.api.message.MessageEntityReq;
import com.ua.spring.domain.response.Response;
import com.ua.spring.domain.response.SuccessResponse;
import com.ua.spring.domain.response.exception.CommonException;
import com.ua.spring.dto.entity.Message;
import com.ua.spring.dto.repository.MessageRepo;
import com.ua.spring.service.communication.MessageService;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
@Data
@Builder
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepo messageRepo;
    @Override
    public ResponseEntity<Response> addMessage(MessageReq messageReq) {

        if(messageRepo.existsByEmail(messageReq.getEmail()) && !messageRepo.findByEmail(messageReq.getEmail()).getIsProcessed()){
            throw CommonException.builder().httpStatus(HttpStatus.BAD_REQUEST).code(Code.EMAIL_EXIST).message("Ваше сообщнение ещё не обработано ждите ответ").build();
        }
        messageRepo.save(Message.builder().message(messageReq.getMessage()).isProcessed(false).statusMessage(StatusMessage.NEW).email(messageReq.getEmail()).localDateTime(new Date(System.currentTimeMillis() + 24000 * 60 * 24)).build());
        return new ResponseEntity<>(SuccessResponse.builder().data("Message sends success").build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> getAllMessage() {
        List<Message> messages = messageRepo.findAll().stream().toList();
        List<MessageEntityReq> messageEntityReqs = new ArrayList<>();
        
        
        messages.forEach(message -> {
        	if(message.getLocalDateTime().before(new Date(System.currentTimeMillis()))) {
        		message.setStatusMessage(StatusMessage.OLD);
    		}
        	messageEntityReqs.add(MessageEntityReq.builder()
        			.id(message.getId())
        			.message(message.getMessage())
        			.email(message.getEmail())
        			.isProcessed(message.getIsProcessed())
        			.statusMessage(message.getStatusMessage()).build());
    	});
        
        Collections.reverse(messageEntityReqs);
        return new ResponseEntity<>(SuccessResponse.builder().data(messageEntityReqs).build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> processedMessage(Long id) {
        Message message = messageRepo.findById(id).get();
        if(!message.getIsProcessed()){
            message.setIsProcessed(true);
            messageRepo.save(message);
            return new ResponseEntity<>(SuccessResponse.builder().data("Success precessed").build(), HttpStatus.OK);
        }
        throw CommonException.builder().httpStatus(HttpStatus.BAD_REQUEST).code(Code.MESSAGE_IS_PROCESSED).message("Сообщение уже обработоно").build();
    }
}
