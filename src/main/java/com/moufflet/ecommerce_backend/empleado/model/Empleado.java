package com.moufflet.ecommerce_backend.empleado.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.moufflet.ecommerce_backend.oficina.model.Oficina;
import com.moufflet.ecommerce_backend.tercero.domain.Tercero;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = false)
@ToString(exclude = {"oficina"})
public class Empleado extends Tercero {

  @Enumerated(EnumType.STRING)
  private EmpleadoRol rol;

  @ManyToOne
  private Oficina oficina;

  @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
  @OnDelete(action = OnDeleteAction.SET_NULL)
  private Empleado jefe;

}
