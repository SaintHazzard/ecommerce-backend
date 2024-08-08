package com.moufflet.ecommerce_backend.pedido.infraestructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moufflet.ecommerce_backend.pedido.application.PedidoProductoRepositoryPort;
import com.moufflet.ecommerce_backend.pedido.model.PedidoProducto;
import com.moufflet.ecommerce_backend.pedido.model.PedidoProductoId;

@Repository
public interface PedidoProductoRepository
    extends JpaRepository<PedidoProducto, PedidoProductoId>, PedidoProductoRepositoryPort {
}
