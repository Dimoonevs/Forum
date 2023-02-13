package com.ua.spring.service.communication;

import com.ua.spring.domain.api.commun.communRequest.CommentReq;
import com.ua.spring.domain.response.Response;
import org.springframework.http.ResponseEntity;

public interface CommentsService {

    ResponseEntity<Response> addComments(CommentReq commentReq, String jwt, Long id);
    ResponseEntity<Response> getComments();


}
