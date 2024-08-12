package com.moufflet.ecommerce_backend.formaPagoTercero.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FormaPagoTerceroDTO {
  private Long id;
  private Long formaPagoId;
  private String terceroId;
  private String nombre;
  private String apellido;
  private String formaPagoNombre;
  private String empleadoId;
  private String empleadoNombre;
  private LocalDate fechaPago;
}
