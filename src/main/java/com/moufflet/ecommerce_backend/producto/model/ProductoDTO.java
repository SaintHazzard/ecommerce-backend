package com.moufflet.ecommerce_backend.producto.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {
  private Long id;
  private String codigo;
  private String nombre;
  private String descripcion;
  private BigDecimal precio;
  private Long gamaId;
  private String proveedorId;
  private byte[] imagen;
  private boolean estado;
}
