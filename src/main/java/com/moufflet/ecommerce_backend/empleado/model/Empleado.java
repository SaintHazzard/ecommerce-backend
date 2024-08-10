package com.moufflet.ecommerce_backend.empleado.model;

import com.moufflet.ecommerce_backend.oficina.model.Oficina;
import com.moufflet.ecommerce_backend.tercero.domain.Tercero;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = false)
public class Empleado extends Tercero {

  @Enumerated(EnumType.STRING)
  private EmpleadoRol rol;

  @ManyToOne
  private Oficina oficina;

  private Empleado jefe;

}
