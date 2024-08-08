package com.moufflet.ecommerce_backend.pedido.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class PedidoProductoId implements Serializable {
  private Long pedido;
  private Long producto;
}