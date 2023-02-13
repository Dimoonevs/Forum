package com.ua.spring.service.communication.impl;

import com.ua.spring.domain.api.commun.communResponse.CommentResp;
import com.ua.spring.domain.api.theme.ThemeReq;
import com.ua.spring.domain.api.theme.ThemeResp;
import com.ua.spring.domain.response.Response;
import com.ua.spring.domain.response.SuccessResponse;
import com.ua.spring.dto.entity.Comments;
import com.ua.spring.dto.entity.Theme;
import com.ua.spring.dto.repository.CommentsRepo;
import com.ua.spring.dto.repository.ThemeRepo;
import com.ua.spring.service.communication.ThemeService;
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
public class ThemeServiceImpl implements ThemeService {
    private final ThemeRepo themeRepo;
    private final CommentsRepo commentsRepo;
    @Override
    public ResponseEntity<Response> createTheme(ThemeReq name) {
        themeRepo.save(Theme.builder().name(name.getName()).text(name.getText()).build());
        return new ResponseEntity<>(SuccessResponse.builder().data("Successful!!").build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> findCommentsByTagsId(Long id) {
        Theme theme = themeRepo.findById(id).get();
        List<Comments> comments = commentsRepo.findByThemes(theme);
        List<CommentResp> commentResp = new ArrayList<>();

        for (Comments comments1:
                comments) {
            commentResp.add(CommentResp.builder()
                    .textComment(comments1.getText())
                    .role(comments1.getUser().getRole())
                    .username(comments1.getUser().getUsername())
                    .isActive(comments1.getUser().getIsActive()).build());
        }

        return new ResponseEntity<>(SuccessResponse.builder().data(ThemeResp.builder().commentResp(commentResp.stream().toList()).theme(theme).build()).build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> findAllTheme() {
        List<Theme> themes = themeRepo.findAll();
        return new ResponseEntity<>(SuccessResponse.builder().data(themes).build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> searchThemeByName(String name) {
        List<Theme> themes = themeRepo.findByName(name);
        return new ResponseEntity<>(SuccessResponse.builder().data(themes).build(), HttpStatus.OK);
    }


}
