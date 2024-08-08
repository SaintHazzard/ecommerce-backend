package com.moufflet.ecommerce_backend.proveedor.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moufflet.ecommerce_backend.proveedor.application.port.ProveedorRepositoryPort;
import com.moufflet.ecommerce_backend.proveedor.model.Proveedor;
import com.moufflet.ecommerce_backend.proveedor.model.ProveedorDTO;

@Service
public class ProveedorService {

  @Autowired
  private ProveedorRepositoryPort proveedorRepositoryPort;

  public Proveedor save(Proveedor proveedor) {
    return proveedorRepositoryPort.save(proveedor);
  }

  public Proveedor findById(String id) {
    return proveedorRepositoryPort.findById(id).orElse(null);
  }

  public void deleteById(String id) {
    proveedorRepositoryPort.deleteById(id);
  }

  public List<ProveedorDTO> findAll() {
    List<Proveedor> proveedores = proveedorRepositoryPort.findAll();
    return proveedores.stream().map(this::convertToDto).toList();
  }

  public ProveedorDTO convertToDto(Proveedor proveedor) {
    return ProveedorDTO.builder()
        .id(proveedor.getId())
        .nombre(proveedor.getPrimerNombre() + proveedor.getPrimerApellido() + proveedor.getSegundoApellido())
        .descripcion(proveedor.getDescripcion())
        .build();
  }

}
