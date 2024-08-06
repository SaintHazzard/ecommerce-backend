package com.moufflet.ecommerce_backend.ciudad.application.port;

import java.util.List;
import java.util.Optional;

import com.moufflet.ecommerce_backend.ciudad.model.Ciudad;

public interface CiudadRepositoryPort {
  Ciudad save(Ciudad ciudad);

  Optional<Ciudad> findById(String id);

  void deleteById(String id);

  List<Ciudad> findAll();

  Optional<Ciudad> findByNombre(String nombre);
}
