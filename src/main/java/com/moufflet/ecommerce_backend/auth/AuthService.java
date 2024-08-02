package com.moufflet.ecommerce_backend.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.moufflet.ecommerce_backend.auth.jwt.JwtService;
import com.moufflet.ecommerce_backend.tercero.application.port.out.TerceroRepositoryPort;
import com.moufflet.ecommerce_backend.tercero.domain.RolTercero;
import com.moufflet.ecommerce_backend.tercero.domain.Tercero;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final TerceroRepositoryPort terceroRepositoryPort;
  private final JwtService jwtService;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;

  public AuthResponse login(LoginRequest request) {
    authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
    UserDetails user = terceroRepositoryPort.findByUsername(request.getUsername()).orElseThrow();
    String token = jwtService.getToken(user);
    return AuthResponse.builder()
        .token(token)
        .build();
  }

  public AuthResponse register(RegisterRequest request) {
    if (request.getId() == null || request.getId().isEmpty()) {
      throw new IllegalArgumentException("ID (cédula) no puede estar vacío");
    }

    Tercero tercero = Tercero.builder()
        .id(request.getId()) // Asegúrate de asignar el ID aquí
        .username(request.getUsername())
        .password(passwordEncoder.encode(request.getPassword()))
        .email(request.getEmail())
        .primerNombre(request.getPrimerNombre())
        .primerApellido(request.getPrimerApellido())
        .telefono(request.getTelefono())
        .rol(RolTercero.USER)
        .build();

    terceroRepositoryPort.save(tercero);

    return AuthResponse.builder().token(jwtService.getToken(tercero)).build();
  }
}
