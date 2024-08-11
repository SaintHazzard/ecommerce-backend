package com.moufflet.ecommerce_backend.oficinaProducto.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OficinaProductoDTO {
  private Long oficinaId;
  private Long productoId;
  private String oficinaNombre;
  private String productoNombre;
  private BigDecimal precio;
  private Integer stock;
}