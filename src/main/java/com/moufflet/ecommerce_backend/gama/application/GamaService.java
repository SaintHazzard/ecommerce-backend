package com.moufflet.ecommerce_backend.gama.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moufflet.ecommerce_backend.gama.application.port.GamaRepositoryPort;
import com.moufflet.ecommerce_backend.gama.model.Gama;

@Service
public class GamaService {

  @Autowired
  private GamaRepositoryPort gamaRepositoryPort;

  public Gama createGama(Gama gama) {
    return gamaRepositoryPort.save(gama);
  }

  public Gama findById(Long id) {
    return gamaRepositoryPort.findById(id).orElse(null);
  }

  public void deleteById(Long id) {
    gamaRepositoryPort.deleteById(id);
  }

  public List<Gama> getAll() {
    return gamaRepositoryPort.findAll();
  }
}
