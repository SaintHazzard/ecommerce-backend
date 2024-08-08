package com.moufflet.ecommerce_backend.pedido.application;

import java.util.List;
import java.util.Optional;

import com.moufflet.ecommerce_backend.pedido.model.Pedido;

public interface PedidoRepositoryPort {
  Pedido save(Pedido pedido);

  Optional<Pedido> findById(Long id);

  List<Pedido> findAll();

  void deleteById(Long id);

  List<Pedido> findByEstado(String estado);

}
