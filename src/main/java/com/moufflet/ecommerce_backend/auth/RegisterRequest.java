package com.moufflet.ecommerce_backend.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
  String id;
  String username;
  String password;
  String email;
  String primerNombre;
  String primerApellido;
  String telefono;
}
