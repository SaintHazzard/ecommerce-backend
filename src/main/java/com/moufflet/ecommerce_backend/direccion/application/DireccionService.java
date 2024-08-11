package com.moufflet.ecommerce_backend.direccion.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moufflet.ecommerce_backend.ciudad.application.CiudadService;
import com.moufflet.ecommerce_backend.direccion.model.Direccion;
import com.moufflet.ecommerce_backend.direccion.model.DireccionDTO;

@Service
public class DireccionService {

  @Autowired
  private DireccionRepositoryPort direccionRepositoryPort;

  @Autowired
  private CiudadService ciudadService;

  public Direccion save(DireccionDTO direccion) {
    Direccion direccionExistente = direccionRepositoryPort
        .findByTipoCalleAndNombreCalleAndNumeroCalleAndNumeroComplementoAndCiudadId(
            direccion.getTipoCalle(), direccion.getNombreCalle(), direccion.getNumeroCalle(),
            direccion.getNumeroComplemento(),
            ciudadService.getPorNombre(direccion.getCiudad()).getId())
        .orElse(null);
    if (direccionExistente == null) {
      return direccionRepositoryPort.save(convertDireccionDTO(direccion));
    } else {
      return direccionExistente;
    }
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

  public DireccionDTO toDTO(Direccion direccion) {
    return DireccionDTO.builder()
        .id(direccion.getId())
        .tipoCalle(direccion.getTipoCalle())
        .nombreCalle(direccion.getNombreCalle())
        .numeroCalle(direccion.getNumeroCalle())
        .numeroComplemento(direccion.getNumeroComplemento())
        .codigoPostal(direccion.getCodigoPostal())
        .ciudad(direccion.getCiudad().getNombre())
        .build();
  }

  public Direccion convertDireccionDTO(DireccionDTO direccionDTO) {
    return Direccion.builder()
        .id(direccionDTO.getId())
        .tipoCalle(direccionDTO.getTipoCalle())
        .nombreCalle(direccionDTO.getNombreCalle())
        .numeroCalle(direccionDTO.getNumeroCalle())
        .numeroComplemento(direccionDTO.getNumeroComplemento())
        .codigoPostal(direccionDTO.getCodigoPostal())
        .ciudad(ciudadService.getPorNombre(direccionDTO.getCiudad()))
        .build();
  }

  public Direccion existDireccion(DireccionDTO direccion) {
    return direccionRepositoryPort.findByTipoCalleAndNombreCalleAndNumeroCalleAndNumeroComplementoAndCiudadId(
        direccion.getTipoCalle(), direccion.getNombreCalle(), direccion.getNumeroCalle(),
        direccion.getNumeroComplemento(), direccion.getCiudad()).orElse(null);
  }
}
