package com.moufflet.ecommerce_backend.ciudad.application;

import java.util.List;

import org.springframework.stereotype.Service;

import com.moufflet.ecommerce_backend.ciudad.application.port.CiudadRepositoryPort;
import com.moufflet.ecommerce_backend.ciudad.model.Ciudad;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CiudadService {

  private final CiudadRepositoryPort ciudadRepositoryPort;

  public Ciudad createCiudad(Ciudad ciudad) {
    return ciudadRepositoryPort.save(ciudad);
  }

  public Ciudad findById(String id) {
    return ciudadRepositoryPort.findById(id).orElse(null);
  }

  public void deleteById(String id) {
    ciudadRepositoryPort.deleteById(id);
  }

  public List<Ciudad> getAll() {
    return ciudadRepositoryPort.findAll();
  }

  public Ciudad getPorNombre(String name) {
    return ciudadRepositoryPort.findByNombre(name).orElse(null);
  }
}
