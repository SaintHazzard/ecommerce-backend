package com.moufflet.ecommerce_backend.pedido.model;

import java.math.BigDecimal;

import com.moufflet.ecommerce_backend.producto.model.Producto;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PedidoProducto {
  @EmbeddedId
  private PedidoProductoId id;

  @ManyToOne
  private Pedido pedido;

  @ManyToOne
  private Producto producto;

  private int cantidad;

  private BigDecimal precio;
}