package com.moufflet.ecommerce_backend.cliente.application;

import java.util.List;
import java.util.Optional;

import com.moufflet.ecommerce_backend.cliente.domain.Cliente;

public interface ClienteRepositoryPort {
  Cliente save(Cliente cliente);

  Optional<Cliente> findById(String id);

  void deleteById(String id);

  Optional<Cliente> findByEmail(String email);

  Optional<Cliente> findByTelefono(String telefono);

  List<Cliente> findAll();
}
