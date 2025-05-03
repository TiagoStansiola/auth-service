package com.truco.auth_service.service.impl;

import com.truco.auth_service.dto.request.LoginRequest;
import com.truco.auth_service.dto.request.RegisterRequest;
import com.truco.auth_service.dto.response.AuthResponse;
import com.truco.auth_service.model.User;

import com.truco.auth_service.respository.UserRepository;
import com.truco.auth_service.security.JwtTokenProvider;
import com.truco.auth_service.service.AuthService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public AuthResponse register(RegisterRequest registerRequest) {

        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());
        User user = new User(registerRequest.getUsername(), encodedPassword, registerRequest.getRole());
        userRepository.save(user);

        String token = jwtTokenProvider.generateToken(user.getUsername());
        return new AuthResponse(user.getUsername(), token);
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername());
        if (user != null && passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            String token = jwtTokenProvider.generateToken(user.getUsername());
            return new AuthResponse(user.getUsername(), token);
        }
        throw new RuntimeException("Invalid username or password");
    }
}
