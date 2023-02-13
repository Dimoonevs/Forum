package com.ua.spring.service.communication.impl;

import com.ua.spring.domain.api.Code;
import com.ua.spring.domain.api.commun.communRequest.CommentReq;
import com.ua.spring.domain.api.commun.communResponse.CommentResp;
import com.ua.spring.domain.response.Response;
import com.ua.spring.domain.response.SuccessResponse;
import com.ua.spring.domain.response.exception.CommonException;
import com.ua.spring.dto.entity.Comments;
import com.ua.spring.dto.entity.Theme;
import com.ua.spring.dto.repository.CommentsRepo;
import com.ua.spring.dto.repository.ThemeRepo;
import com.ua.spring.dto.repository.UserRepo;
import com.ua.spring.service.communication.CommentsService;
import com.ua.spring.service.jwt.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
@Builder
@RequiredArgsConstructor
public class CommentsServiceImpl implements CommentsService {
    private final CommentsRepo commentsRepo;
    private final UserRepo userRepo;
    private final HttpServletRequest httpServletRequest;
    private final JwtService jwtService;
    private final ThemeRepo themeRepo;
    @Override
    public ResponseEntity<Response> addComments(CommentReq commentReq, String jwt, Long id) {

        String username = jwtService.extractUsername(jwt.substring(7));
        if (username == null){
            throw CommonException.builder().code(Code.AUTHORIZATION_ERROR).message("Неверный токен").httpStatus(HttpStatus.BAD_REQUEST).build();
        }
        Theme theme = themeRepo.findById(id).get();
        commentsRepo.save(Comments.builder().text(commentReq.getText()).themes(theme).user(userRepo.findByUsername(username)).build());
        return new ResponseEntity<>(SuccessResponse.builder().data("Success create").build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> getComments() {
        List<Comments> comments = commentsRepo.findAll();
        List<CommentResp> commentResp = new ArrayList<>();

        for (Comments comments1:
             comments) {
            commentResp.add(CommentResp.builder()
                    .textComment(comments1.getText())
                    .role(comments1.getUser().getRole())
                    .username(comments1.getUser().getUsername()).build());
        }

        return new ResponseEntity<>(SuccessResponse.builder().data(commentResp).build(), HttpStatus.OK);
    }

}
