package com.moufflet.ecommerce_backend.direccion.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moufflet.ecommerce_backend.direccion.model.Direccion;

@Service
public class DireccionService {

  @Autowired
  private DireccionRepositoryPort direccionRepositoryPort;

  public Direccion save(Direccion direccion) {
    return direccionRepositoryPort.save(direccion);
  }

  public Direccion getById(Long id) {
    return direccionRepositoryPort.findById(id).orElseThrow(null);
  }

  public void deleteById(Long id) {
    direccionRepositoryPort.deleteById(id);
  }

  public Direccion getByCodigoPostal(String codigoPostal) {
    return direccionRepositoryPort.findByCodigoPostal(codigoPostal).orElseThrow(null);
  }
}
