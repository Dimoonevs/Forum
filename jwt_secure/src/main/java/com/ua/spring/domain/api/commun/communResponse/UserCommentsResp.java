package com.ua.spring.domain.api.commun.communResponse;

import com.ua.spring.dto.entity.Comments;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCommentsResp {
    private String username;
    private List<Comments> comments;
}
