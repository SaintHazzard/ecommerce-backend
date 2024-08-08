package com.moufflet.ecommerce_backend.pedido.model;

import java.math.BigDecimal;

import jakarta.persistence.EmbeddedId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PedidoProductoDTO {
  @EmbeddedId
  private PedidoProductoId id;

  private int cantidad;

  private BigDecimal precio;
}
