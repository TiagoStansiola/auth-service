package com.truco.auth_service.dto.response;

import lombok.Data;

@Data
public class AuthResponse {
    private String username;
    private String token;

    public AuthResponse(String username, String token) {
        this.username = username;
        this.token = token;
    }
}

