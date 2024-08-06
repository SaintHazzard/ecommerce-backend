package com.moufflet.ecommerce_backend.proveedor.model;

import java.util.List;

import com.moufflet.ecommerce_backend.producto.model.Producto;
import com.moufflet.ecommerce_backend.tercero.domain.Tercero;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Data
public class Proveedor extends Tercero {
  private String descripcion;

  @OneToMany
  private List<Producto> productos;
}
