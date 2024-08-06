package com.moufflet.ecommerce_backend.proveedor.application.port;

import java.util.List;
import java.util.Optional;

import com.moufflet.ecommerce_backend.proveedor.model.Proveedor;

public interface ProveedorRepositoryPort {
  Proveedor save(Proveedor proveedor);

  Optional<Proveedor> findById(String id);

  void deleteById(String id);

  List<Proveedor> findAll();

}
