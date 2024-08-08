package com.moufflet.ecommerce_backend.pedido.application;

import com.moufflet.ecommerce_backend.pedido.model.PedidoProducto;

public interface PedidoProductoRepositoryPort {
  PedidoProducto save(PedidoProducto pedidoProducto);
}
