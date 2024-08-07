package com.moufflet.ecommerce_backend.tercero.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moufflet.ecommerce_backend.auth.AuthService;
import com.moufflet.ecommerce_backend.auth.RegisterRequest;
import com.moufflet.ecommerce_backend.cliente.application.ClienteService;
import com.moufflet.ecommerce_backend.cliente.domain.Cliente;
import com.moufflet.ecommerce_backend.tercero.application.port.out.TerceroRepositoryPort;
import com.moufflet.ecommerce_backend.tercero.domain.Tercero;

@Service
public class TerceroService {

  @Autowired
  private TerceroRepositoryPort terceroRepositoryPort;

  @Autowired
  private ClienteService clienteService;

  @Autowired
  private AuthService authService;

  public Tercero save(Tercero tercero) {
    return terceroRepositoryPort.save(tercero);
  }

  public void deleteById(String id) {
    terceroRepositoryPort.deleteById(id);
  }

  public boolean existsById(String id) {
    return terceroRepositoryPort.existsById(id);
  }

  public void deleteByUsername(String username) {
    terceroRepositoryPort.findByUsername(username).ifPresent(terceroRepositoryPort::delete);
  }

  public void deleteByEmail(String email) {
    terceroRepositoryPort.findByEmail(email).ifPresent(terceroRepositoryPort::delete);
  }

  public Tercero getById(String id) {
    return terceroRepositoryPort.findById(id).orElse(null);
  }

  public Tercero getByUsername(String username) {
    return terceroRepositoryPort.findByUsername(username).orElse(null);
  }

  public List<Tercero> getAll() {
    return terceroRepositoryPort.findAll();
  }

  public Tercero setNuevoCliente(RegisterRequest request) {
    authService.register(request);

    return clienteService.save(new Cliente(request.getId()));
  }

}
