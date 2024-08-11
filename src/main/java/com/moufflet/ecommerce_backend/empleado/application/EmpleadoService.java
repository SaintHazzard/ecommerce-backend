package com.moufflet.ecommerce_backend.empleado.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moufflet.ecommerce_backend.empleado.model.Empleado;
import com.moufflet.ecommerce_backend.empleado.model.EmpleadoDTO;
import com.moufflet.ecommerce_backend.empleado.model.EmpleadoRol;
import com.moufflet.ecommerce_backend.oficina.application.OficinaService;
import com.moufflet.ecommerce_backend.oficina.model.Oficina;
import com.moufflet.ecommerce_backend.tercero.application.TerceroService;
import com.moufflet.ecommerce_backend.tercero.domain.Tercero;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class EmpleadoService {

  @Autowired
  private EmpleadoRepositoryPort empleadoRepositoryPort;

  @Autowired
  private OficinaService oficinaService;

  @Autowired
  private TerceroService terceroService;

  @PersistenceContext
  private EntityManager entityManager;

  @Transactional
  public Empleado save(EmpleadoDTO empleadoDTO) {
    return empleadoRepositoryPort.save(fromDTO(empleadoDTO));
  }

  @Transactional
  public Empleado save(Empleado empleado) {
    return empleadoRepositoryPort.save(empleado);
  }

  public Empleado update(String id, EmpleadoDTO empleadoDTO) {
    Empleado empleado = getById(id);
    if (empleado == null) {
      return null;
    }
    empleado.setPrimerNombre(empleadoDTO.getPrimerNombre());
    empleado.setPrimerApellido(empleadoDTO.getPrimerApellido());
    empleado.setEmail(empleadoDTO.getEmail());
    empleado.setTelefono(empleadoDTO.getTelefono());
    empleado.setRol(EmpleadoRol.valueOf(empleadoDTO.getRol()));
    if (empleadoDTO.getJefe() != null && !empleadoDTO.getJefe().isEmpty()) {
      empleado.setJefe(getById(empleadoDTO.getJefe()));
    } else {
      empleado.setJefe(null);
    }
    if (empleadoDTO.getOficina() != null) {
      empleado.setOficina(oficinaService.getById(empleadoDTO.getOficina()));
    }
    return empleadoRepositoryPort.save(empleado);
  }

  public List<EmpleadoDTO> getByOficinaCiudadNombre(String ciudad) {
    List<Empleado> empleados = empleadoRepositoryPort.findByOficinaDireccionCiudadNombre(ciudad);
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
    System.out.println(empleadoDTO);
    if (empleadoDTO.getId() != null) {
      empleado.setId(empleadoDTO.getId());
    }
    empleado.setPrimerNombre(empleadoDTO.getPrimerNombre());
    empleado.setPrimerApellido(empleadoDTO.getPrimerApellido());
    empleado.setEmail(empleadoDTO.getEmail());
    empleado.setTelefono(empleadoDTO.getTelefono());
    empleado.setRol(EmpleadoRol.valueOf(empleadoDTO.getRol()));

    if (empleadoDTO.getJefe() != null && !empleadoDTO.getJefe().isEmpty()) {
      empleado.setJefe(getById(empleadoDTO.getJefe()));
    } else {
      empleado.setJefe(null);
    }

    if (empleadoDTO.getOficina() != null) {
      empleado.setOficina(oficinaService.getById(empleadoDTO.getOficina()));
    } else {
      empleado.setOficina(null);
    }

    return empleado;
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

  public EmpleadoDTO toDTO(Empleado empleado, Oficina oficina) {
    return EmpleadoDTO.builder()
        .id(empleado.getId())
        .primerNombre(empleado.getPrimerNombre())
        .primerApellido(empleado.getPrimerApellido())
        .email(empleado.getEmail())
        .telefono(empleado.getTelefono())
        .rol(empleado.getRol().name())
        .oficinaNombre(oficina.getNombre())
        .jefe(empleado.getJefe() != null
            ? empleado.getJefe().getPrimerNombre() + " " + empleado.getJefe().getPrimerApellido()
            : null)
        .build();
  }

  public EmpleadoDTO toDTO(Empleado empleado, Oficina oficina, Empleado jefe) {
    return EmpleadoDTO.builder()
        .id(empleado.getId())
        .primerNombre(empleado.getPrimerNombre())
        .primerApellido(empleado.getPrimerApellido())
        .email(empleado.getEmail())
        .telefono(empleado.getTelefono())
        .rol(empleado.getRol().name())
        .oficinaNombre(oficina.getNombre())
        .jefe(jefe.getPrimerNombre() + " " + jefe.getPrimerApellido())
        .build();
  }

  public EmpleadoDTO toDTO(Empleado empleado, Oficina oficina, Empleado jefe, List<Empleado> subalternos) {
    return EmpleadoDTO.builder()
        .id(empleado.getId())
        .primerNombre(empleado.getPrimerNombre())
        .primerApellido(empleado.getPrimerApellido())
        .email(empleado.getEmail())
        .telefono(empleado.getTelefono())
        .rol(empleado.getRol().name())
        .oficinaNombre(oficina.getNombre())
        .jefe(jefe.getPrimerNombre() + " " + jefe.getPrimerApellido())
        .build();
  }
}