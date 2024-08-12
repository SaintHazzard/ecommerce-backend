package com.moufflet.ecommerce_backend.pedido.application;

import java.util.List;


import com.moufflet.ecommerce_backend.pedido.model.PedidoProductoDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PedidoRequest {
  private PedidoDTO pedido;
  private List<PedidoProductoDTO> productos;
}
