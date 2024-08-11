package com.moufflet.ecommerce_backend.Formapago.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FormaPagoDTO {
  private Long id;
  private String nombre;
}
