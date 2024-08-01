package com.moufflet.ecommerce_backend.auth.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moufflet.ecommerce_backend.auth.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  @Autowired
  AuthService authService;

  @PostMapping("/login")
  public ResponseEntity<AuthResponse> login(LoginRequest loginRequest) {
    return ResponseEntity.ok(authService.login(loginRequest));
  }

  @PostMapping("/register")
  public ResponseEntity<AuthResponse> register(RegisterRequest registerRequest) {
    return ResponseEntity.ok(authService.register(registerRequest));
  }

}
