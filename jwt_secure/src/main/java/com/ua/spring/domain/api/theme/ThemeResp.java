package com.ua.spring.domain.api.theme;

import com.ua.spring.domain.api.commun.communResponse.CommentResp;
import com.ua.spring.dto.entity.Theme;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ThemeResp {
    private Theme theme;
    private List<CommentResp> commentResp;
}
