package com.moufflet.ecommerce_backend.proveedor.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProveedorDTO {
  private String id;
  private String nombre;
  private String descripcion;

}
