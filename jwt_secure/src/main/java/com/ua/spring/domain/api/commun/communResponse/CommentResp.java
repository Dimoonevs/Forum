package com.ua.spring.domain.api.commun.communResponse;

import com.ua.spring.domain.api.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentResp {
    private String textComment;
    private String username;
    private Role role;
    private Boolean isActive;

}
