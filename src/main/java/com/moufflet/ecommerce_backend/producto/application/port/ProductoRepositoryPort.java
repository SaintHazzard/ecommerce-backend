package com.moufflet.ecommerce_backend.producto.application.port;

import java.util.List;
import java.util.Optional;

import com.moufflet.ecommerce_backend.producto.model.Producto;

public interface ProductoRepositoryPort {

  Producto save(Producto producto);

  Optional<Producto> findById(Long id);

  void deleteById(Long id);

  List<Producto> findAll();

}
