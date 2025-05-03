package com.truco.auth_service.controller;

import com.truco.auth_service.dto.request.LoginRequest;
import com.truco.auth_service.dto.request.RegisterRequest;
import com.truco.auth_service.dto.response.AuthResponse;
import com.truco.auth_service.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    private static final String LOGIN  = "/login";
    private static final String  REGISTER = "/register";

    @PostMapping(LOGIN)
    @Operation(summary = "Loguear usuario con usuario y contraseña")
    public AuthResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping(REGISTER)
    public AuthResponse register(@RequestBody RegisterRequest registerRequest) {
        return authService.register(registerRequest);
    }
}

