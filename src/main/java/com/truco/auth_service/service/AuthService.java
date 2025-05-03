package com.truco.auth_service.service;

import com.truco.auth_service.dto.request.LoginRequest;
import com.truco.auth_service.dto.request.RegisterRequest;
import com.truco.auth_service.dto.response.AuthResponse;

public interface AuthService {
    AuthResponse register(RegisterRequest registerRequest);
    AuthResponse login(LoginRequest loginRequest);
}
