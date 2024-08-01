package com.moufflet.ecommerce_backend.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moufflet.ecommerce_backend.auth.jwt.AuthResponse;
import com.moufflet.ecommerce_backend.auth.jwt.LoginRequest;
import com.moufflet.ecommerce_backend.auth.jwt.RegisterRequest;
import com.moufflet.ecommerce_backend.tercero.application.port.out.TerceroRepositoryPort;
import com.moufflet.ecommerce_backend.tercero.domain.RolTercero;
import com.moufflet.ecommerce_backend.tercero.domain.Tercero;

@Service
public class AuthService {

  @Autowired
  private TerceroRepositoryPort terceroRepositoryPort;

  @Autowired
  private JwtService jwtService;

  public AuthResponse login(LoginRequest loginRequest) {
    return AuthResponse.builder().token("token").build();
  }

  public AuthResponse register(RegisterRequest registerRequest) {
    Tercero tercero = Tercero.builder().username(registerRequest.getUsername()).password(registerRequest.getPassword())
        .email(registerRequest.getEmail()).primerNombre(registerRequest.getPrimerNombre())
        .segundoNombre(registerRequest.getSegundoNombre()).primerApellido(registerRequest.getPrimerApellido())
        .segundoApellido(registerRequest.getSegundoApellido()).telefono(registerRequest.getTelefono())
        .rol(RolTercero.USER).build();

    terceroRepositoryPort.save(tercero);

    return AuthResponse.builder().token(jwtService.getToken(tercero)).build();
  }
}
