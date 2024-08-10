package com.moufflet.ecommerce_backend.oficina.application;

import java.util.List;
import java.util.Optional;

import com.moufflet.ecommerce_backend.oficina.model.Oficina;

public interface OficinaRepositoryPort {

  Oficina save(Oficina oficina);

  Optional<Oficina> findById(Long id);

  List<Oficina> findAll();

  void deleteById(Long id);

  Optional<Oficina> findByTelefono(String telefono);

  Optional<Oficina> findByDireccionCiudadNombre(String ciudad);

  Optional<Oficina> findByNombre(String nombre);

}
