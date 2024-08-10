package com.moufflet.ecommerce_backend.oficina.application;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moufflet.ecommerce_backend.direccion.application.DireccionService;
import com.moufflet.ecommerce_backend.direccion.model.Direccion;
import com.moufflet.ecommerce_backend.direccion.model.DireccionDTO;
import com.moufflet.ecommerce_backend.empleado.model.Empleado;
import com.moufflet.ecommerce_backend.empleado.model.EmpleadoDTO;
import com.moufflet.ecommerce_backend.oficina.model.Oficina;
import com.moufflet.ecommerce_backend.oficina.model.OficinaDTO;

@Service
public class OficinaService {

  @Autowired
  private OficinaRepositoryPort oficinaRepositoryPort;

  @Autowired
  private DireccionService direccionService;

  public List<Oficina> getAllOficinas() {
    return oficinaRepositoryPort.findAll();
  }

  public Oficina save(Oficina oficina) {
    return oficinaRepositoryPort.save(oficina);
  }

  public Oficina getById(Long id) {
    if (id == null) {
      return null;
    }
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

  public List<EmpleadoDTO> getByOficina(String nombre) {
    List<Empleado> empleOfi = oficinaRepositoryPort.findByNombre(nombre).orElse(null).getEmpleados();
    return empleOfi.stream().map(empleado -> toDTO(empleado)).collect(Collectors.toList());
  }

  public List<EmpleadoDTO> getByOficina(Long id) {
    List<Empleado> empleOfi = oficinaRepositoryPort.findById(id).orElse(null).getEmpleados();
    return empleOfi.stream().map(empleado -> toDTO(empleado)).collect(Collectors.toList());
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

  public EmpleadoDTO toDTO(Empleado empleado) {
    return EmpleadoDTO.builder()
        .id(empleado.getId())
        .primerNombre(empleado.getPrimerNombre())
        .primerApellido(empleado.getPrimerApellido())
        .email(empleado.getEmail())
        .telefono(empleado.getTelefono())
        .rol(empleado.getRol().name())
        .oficinaNombre(empleado.getOficina() != null ? empleado.getOficina().getNombre() : null)
        .jefe(empleado.getJefe() != null
            ? empleado.getJefe().getPrimerNombre() + " " + empleado.getJefe().getPrimerApellido()
            : null)
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
