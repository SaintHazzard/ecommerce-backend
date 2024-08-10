package com.moufflet.ecommerce_backend.empleado.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoDTO {
  private String primerNombre;
  private String primerApellido;
  private String email;
  private String telefono;
  private String rol;
  private String oficina;
  private String jefe;

}
