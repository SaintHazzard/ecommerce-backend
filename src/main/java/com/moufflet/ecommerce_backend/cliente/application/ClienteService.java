package com.moufflet.ecommerce_backend.cliente.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moufflet.ecommerce_backend.cliente.domain.Cliente;

@Service
public class ClienteService {

  @Autowired
  private ClienteRepositoryPort clienteRepositoryPort;

  public List<Cliente> findAll() {
    return clienteRepositoryPort.findAll();
  }

  public Cliente save(Cliente cliente) {
    return clienteRepositoryPort.save(cliente);
  }

  public Cliente findById(String id) {
    return clienteRepositoryPort.findById(id).orElse(null);
  }

  public void deleteById(String id) {
    clienteRepositoryPort.deleteById(id);
  }

  public Cliente findByEmail(String email) {
    return clienteRepositoryPort.findByEmail(email).orElse(null);
  }

  public Cliente findByTelefono(String telefono) {
    return clienteRepositoryPort.findByTelefono(telefono).orElse(null);
  }
}
