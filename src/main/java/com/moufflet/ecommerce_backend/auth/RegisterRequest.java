package com.moufflet.ecommerce_backend.auth;

import java.util.List;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterRequest {
  String id;
  String username;
  String password;
  String email;
  String primerNombre;
  String primerApellido;
  String telefono;
  List<Long> role;
}
