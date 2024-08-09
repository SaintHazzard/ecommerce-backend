package com.moufflet.ecommerce_backend.oficina.model;

import com.moufflet.ecommerce_backend.direccion.model.DireccionDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OficinaDTO {
  private Long id;
  private DireccionDTO direccion;
  private String telefono;
}
