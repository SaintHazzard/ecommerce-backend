package com.moufflet.ecommerce_backend.tercero.application.port.out;

import java.util.List;
import java.util.Optional;

import com.moufflet.ecommerce_backend.tercero.domain.Tercero;

public interface TerceroRepositoryPort {

  // Metodos crud

  Tercero save(Tercero tercero);

  void delete(Tercero tercero);

  void deleteById(String id);

  // Metodos de consulta

  Optional<Tercero> findById(String id);

  Optional<Tercero> findByUsername(String username);

  boolean existsById(String id);

  Optional<Tercero> findByEmail(String email);

  List<Tercero> findAll();

}
