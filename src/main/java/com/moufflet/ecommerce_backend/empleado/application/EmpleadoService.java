package com.moufflet.ecommerce_backend.empleado.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moufflet.ecommerce_backend.empleado.model.Empleado;
import com.moufflet.ecommerce_backend.empleado.model.EmpleadoDTO;
import com.moufflet.ecommerce_backend.empleado.model.EmpleadoRol;
import com.moufflet.ecommerce_backend.oficina.application.OficinaService;

@Service
public class EmpleadoService {

  @Autowired
  private EmpleadoRepositoryPort empleadoRepositoryPort;

  @Autowired
  private OficinaService oficinaService;

  public Empleado save(Empleado empleado) {
    return empleadoRepositoryPort.save(empleado);
  }

  public List<EmpleadoDTO> getByOficinaCiudadNombre(String ciudad) {
    List<Empleado> empleados = empleadoRepositoryPort.findByOficinaCiudadNombre(ciudad);
    return empleados.stream().map(empleado -> toDTO(empleado)).toList();
  }

  public void deleteById(String id) {
    empleadoRepositoryPort.deleteById(id);
  }

  public Empleado getById(String id) {
    return empleadoRepositoryPort.findById(id).orElse(null);
  }

  public List<EmpleadoDTO> findAll() {
    List<Empleado> empleados = empleadoRepositoryPort.findAll();
    return empleados.stream().map(empleado -> toDTO(empleado)).toList();
  }

  public Empleado fromDTO(EmpleadoDTO empleadoDTO) {
    Empleado empleado = new Empleado();
    empleado.setPrimerNombre(empleadoDTO.getPrimerNombre());
    empleado.setPrimerApellido(empleadoDTO.getPrimerApellido());
    empleado.setEmail(empleadoDTO.getEmail());
    empleado.setTelefono(empleadoDTO.getTelefono());
    empleado.setRol(EmpleadoRol.valueOf(empleadoDTO.getRol()));
    empleado.setJefe(getById(empleadoDTO.getJefe()));
    empleado.setOficina(oficinaService.getByNombreOficina(empleadoDTO.getOficina()));
    return empleado;
  }

  public EmpleadoDTO toDTO(Empleado empleado) {
    return EmpleadoDTO.builder()
        .primerNombre(empleado.getPrimerNombre())
        .primerApellido(empleado.getPrimerApellido())
        .email(empleado.getEmail())
        .telefono(empleado.getTelefono())
        .rol(empleado.getRol().name())
        .oficina(empleado.getOficina().getNombre())
        .jefe(empleado.getJefe() != null
            ? empleado.getJefe().getPrimerNombre() + " " + empleado.getJefe().getPrimerApellido()
            : null)
        .build();
  }
}