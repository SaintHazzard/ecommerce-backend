package com.moufflet.ecommerce_backend.pedido.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.moufflet.ecommerce_backend.producto.model.Producto;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
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
  @MapsId("pedido")
  @JsonManagedReference
  private Pedido pedido;

  @ManyToOne
  @MapsId("producto")
  private Producto producto;

  private int cantidad;

}