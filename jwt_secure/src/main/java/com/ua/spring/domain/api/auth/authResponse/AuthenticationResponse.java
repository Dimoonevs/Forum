package com.ua.spring.domain.api.auth.authResponse;

import com.ua.spring.domain.api.Role;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String token;
    private Role role;
}
