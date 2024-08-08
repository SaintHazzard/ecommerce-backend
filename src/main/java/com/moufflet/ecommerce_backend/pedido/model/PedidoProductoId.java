package com.moufflet.ecommerce_backend.pedido.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PedidoProductoId implements Serializable {
  
  private Long pedido;
  private Long producto;

  public PedidoProductoId(String pedido, String producto) {
    this.pedido = Long.valueOf(pedido);
    this.producto = Long.valueOf(producto);
  }

  public PedidoProductoId(String producto) {
    this.producto = Long.valueOf(producto);
  }
}