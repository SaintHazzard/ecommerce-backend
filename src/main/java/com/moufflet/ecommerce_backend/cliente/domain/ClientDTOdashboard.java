package com.moufflet.ecommerce_backend.cliente.domain;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientDTOdashboard {
  private String nombre;
  private String apellido;
  private String telefono;
  private BigDecimal cupoCredito;
}
