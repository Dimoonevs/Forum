package com.ua.spring.service.communication.impl;

import com.ua.spring.domain.api.commun.communResponse.CommentResp;
import com.ua.spring.domain.response.Response;
import com.ua.spring.domain.response.SuccessResponse;
import com.ua.spring.dto.entity.Comments;
import com.ua.spring.dto.entity.User;
import com.ua.spring.dto.repository.CommentsRepo;
import com.ua.spring.dto.repository.UserRepo;
import com.ua.spring.service.communication.SearchService;
import com.ua.spring.service.jwt.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@Service
@Data
@Builder
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {
    private final CommentsRepo commentsRepo;
    private final UserRepo userRepo;
    private final HttpServletRequest httpServletRequest;
    private final JwtService jwtService;
    @Override
    public ResponseEntity<Response> getCommentsByUser(String jwt) {
        String username = jwtService.extractUsername(jwt.substring(7));
        User user = userRepo.findByUsername(username);
        List<Comments> comments = commentsRepo.findByUser(user);

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
