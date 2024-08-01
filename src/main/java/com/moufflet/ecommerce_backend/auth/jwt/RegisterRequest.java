package com.moufflet.ecommerce_backend.auth.jwt;

import com.moufflet.ecommerce_backend.tercero.domain.RolTercero;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
  private String username;
  private String password;
  private String email;
  private String primerNombre;
  private String segundoNombre;
  private String primerApellido;
  private String segundoApellido;
  private String telefono;
  private RolTercero rol;
}
