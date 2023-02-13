package com.ua.spring.service.communication;

import com.ua.spring.domain.api.theme.ThemeReq;
import com.ua.spring.domain.response.Response;
import org.springframework.http.ResponseEntity;

public interface ThemeService {
    ResponseEntity<Response> createTheme(ThemeReq name);
    ResponseEntity<Response> findCommentsByTagsId(Long id);
    ResponseEntity<Response> findAllTheme();

    ResponseEntity<Response> searchThemeByName(String name);

}
