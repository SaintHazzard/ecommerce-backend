package com.moufflet.ecommerce_backend.oficinaProducto.application;

import java.util.List;
import java.util.Optional;

import com.moufflet.ecommerce_backend.oficinaProducto.model.OficinaProducto;
import com.moufflet.ecommerce_backend.oficinaProducto.model.OficinaProductoId;

public interface OficinaProductoRepositoryPort {

  OficinaProducto save(OficinaProducto oficinaProducto);

  Optional<OficinaProducto> findById(OficinaProductoId id);

  void deleteById(OficinaProductoId id);

  List<OficinaProducto> findAll();

  boolean existsById(OficinaProductoId id);

}
