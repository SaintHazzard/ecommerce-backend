package com.moufflet.ecommerce_backend.cliente.domain;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientDTOdash {
  private String id;
  private String nombre;
  private String apellido;
  private String email;
  private String telefono;
  private BigDecimal credito;
}
