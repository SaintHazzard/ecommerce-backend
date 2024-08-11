package com.moufflet.ecommerce_backend.tercero.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TerceroDTO {
  private String id;
  private String nombre;
  private String apellido;
  private String email;
  private String telefono;
}
