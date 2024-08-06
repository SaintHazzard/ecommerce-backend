package com.moufflet.ecommerce_backend.direccion.application;

import java.util.List;
import java.util.Optional;

import com.moufflet.ecommerce_backend.direccion.model.Direccion;

public interface DireccionRepositoryPort {
  Direccion save(Direccion direccion);

  Optional<Direccion> findById(Long id);

  void deleteById(Long id);

  Optional<Direccion> findByCodigoPostal(String codigoPostal);

  Optional<Direccion> findByCiudadId(String ciudadId);

  List<Direccion> findAll();

  List<Direccion> findByCiudadNombre(String provinciaNombre);
}
