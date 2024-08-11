package com.moufflet.ecommerce_backend.oficinaProducto.model;

import java.io.Serializable;

import com.moufflet.ecommerce_backend.oficina.model.Oficina;
import com.moufflet.ecommerce_backend.producto.model.Producto;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OficinaProductoId implements Serializable {

  @ManyToOne
  private Oficina oficina;

  @ManyToOne
  private Producto producto;
}
