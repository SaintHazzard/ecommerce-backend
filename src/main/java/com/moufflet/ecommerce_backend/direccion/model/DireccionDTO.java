package com.moufflet.ecommerce_backend.direccion.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DireccionDTO {
  private Long id;
  private String tipoCalle;
  private String numeroCalle;
  private String numeroComplemento;
  private String codigoPostal;
  private String ciudad;

}
