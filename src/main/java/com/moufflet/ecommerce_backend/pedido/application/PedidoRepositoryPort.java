package com.moufflet.ecommerce_backend.pedido.application;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.moufflet.ecommerce_backend.pedido.model.EstadoPedido;
import com.moufflet.ecommerce_backend.pedido.model.Pedido;

public interface PedidoRepositoryPort {
  Pedido save(Pedido pedido);

  Optional<Pedido> findById(Long id);

  List<Pedido> findAll();

  void deleteById(Long id);

  List<Pedido> findByEmpleadoId(String empleadoId);

  List<Pedido> findByEstado(EstadoPedido estado);

  @Query("SELECT p FROM Pedido p WHERE p.estado = :estado")
  List<Pedido> findPedidosByEstado(@Param("estado") EstadoPedido estado);

  // List<Pedido> findByFormaPagoTercero(Long clienteId);
}
