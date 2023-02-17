package com.ua.spring.controller.communication;

import com.ua.spring.domain.api.commun.communRequest.CommentReq;
import com.ua.spring.domain.response.Response;
import com.ua.spring.domain.response.SuccessResponse;
import com.ua.spring.service.communication.CommentsService;
import com.ua.spring.service.communication.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/hello")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class CommunicationController {
    private final CommentsService communicationService;
    private final SearchService searchService;
    @PostMapping("/add_comments")
    public ResponseEntity<Response> addComments(@RequestBody CommentReq commentReq, @RequestHeader String Authorization, @RequestHeader Long id){
        log.info("START point, add comments {}", commentReq);
        return communicationService.addComments(commentReq, Authorization, id);
    }

 
    @GetMapping("/get_user_comments")
    public ResponseEntity<Response> getUserComments(@RequestHeader String Authorization){
        log.info("START point");
        return searchService.getCommentsByUser(Authorization);
    }

}
