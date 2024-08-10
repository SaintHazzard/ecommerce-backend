package com.moufflet.ecommerce_backend.oficina.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moufflet.ecommerce_backend.direccion.application.DireccionService;
import com.moufflet.ecommerce_backend.direccion.model.Direccion;
import com.moufflet.ecommerce_backend.direccion.model.DireccionDTO;
import com.moufflet.ecommerce_backend.oficina.model.Oficina;
import com.moufflet.ecommerce_backend.oficina.model.OficinaDTO;

@Service
public class OficinaService {

  @Autowired
  private OficinaRepositoryPort oficinaRepositoryPort;

  @Autowired
  private DireccionService direccionService;

  public Oficina save(Oficina oficina) {
    return oficinaRepositoryPort.save(oficina);
  }

  public Oficina getById(Long id) {
    return oficinaRepositoryPort.findById(id).orElse(null);
  }

  public void deleteById(Long id) {
    oficinaRepositoryPort.deleteById(id);
  }

  public Oficina getByTelefono(String telefono) {
    return oficinaRepositoryPort.findByTelefono(telefono).orElse(null);
  }

  public Oficina getByDireccionCiudadNombre(String ciudad) {
    return oficinaRepositoryPort.findByDireccionCiudadNombre(ciudad).orElse(null);
  }

  public Oficina getByNombreOficina(String nombre) {
    return oficinaRepositoryPort.findByNombre(nombre).orElse(null);
  }

  public Oficina OficinaToDto(OficinaDTO oficina) {
    Direccion direccion = direccionService.convertDireccionDTO(oficina.getDireccion());
    return Oficina.builder()
        .id(oficina.getId())
        .nombre(oficina.getNombre())
        .direccion(direccion)
        .telefono(oficina.getTelefono())
        .build();
  }

  public OficinaDTO toDTO(Oficina oficina) {
    DireccionDTO direccion = direccionService.toDTO(oficina.getDireccion());
    return OficinaDTO.builder()
        .id(oficina.getId())
        .nombre(oficina.getNombre())
        .direccion(direccion)
        .telefono(oficina.getTelefono())
        .build();
  }

}
