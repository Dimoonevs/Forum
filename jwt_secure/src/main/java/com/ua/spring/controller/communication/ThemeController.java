package com.ua.spring.controller.communication;

import com.ua.spring.domain.api.theme.SearchThemeReq;
import com.ua.spring.domain.api.theme.ThemeReq;
import com.ua.spring.domain.response.Response;
import com.ua.spring.service.communication.ThemeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/theme")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class ThemeController {

    private final ThemeService themeService;
    @PostMapping("/create_theme")
    public ResponseEntity<Response> createTheme(@RequestBody ThemeReq name){
        log.info("START point: {}", name);
        return themeService.createTheme(name);
    }

    @PostMapping("/search_theme")
    public ResponseEntity<Response> searchThemeByName(@RequestBody SearchThemeReq name){
        return themeService.searchThemeByName(name.getName());
    }


    @GetMapping("/get_comments_by_themeId")
    public ResponseEntity<Response> getCommentsByThemeId(@RequestHeader Long id){
        log.info("START point: {}", id);
        return themeService.findCommentsByTagsId(id);
    }


    @GetMapping("/get_all_theme")
    public ResponseEntity<Response> getAllTheme(){
        log.info("START point");
        return themeService.findAllTheme();
    }
}
