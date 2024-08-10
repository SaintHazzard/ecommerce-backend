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
    // Buscar el Tercero asociado por ID
    Empleado tercero = (Empleado) terceroService.getById(empleadoDTO.getId());

    if (tercero != null) {
      // Configurar el ID del Empleado
      Empleado empleadoEntity = fromDTO(empleadoDTO);

      // Verificar si el Empleado ya existe
      Empleado existingEmpleado = empleadoRepositoryPort.findById(tercero.getId()).orElse(null);

      if (existingEmpleado != null) {
        // Actualizar los detalles del Empleado existente
        existingEmpleado.setPrimerNombre(empleadoEntity.getPrimerNombre());
        existingEmpleado.setPrimerApellido(empleadoEntity.getPrimerApellido());
        existingEmpleado.setEmail(empleadoEntity.getEmail());
        existingEmpleado.setTelefono(empleadoEntity.getTelefono());
        existingEmpleado.setRol(empleadoEntity.getRol());
        existingEmpleado.setOficina(empleadoEntity.getOficina());
        existingEmpleado.setJefe(empleadoEntity.getJefe());
        return empleadoRepositoryPort.save(existingEmpleado);
      } else {
        // Si no existe, guardar el nuevo Empleado
        empleadoEntity.setId(tercero.getId());
        return empleadoRepositoryPort.save(empleadoEntity);
      }
    } else {
      // Si el Tercero no existe, crear un nuevo Empleado
      Empleado empleadoEntity = fromDTO(empleadoDTO);
      empleadoEntity.setId(empleadoDTO.getId());
      return empleadoRepositoryPort.save(empleadoEntity);
    }
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
    System.out.println("LLEGA 1------------------------------------------------------------------");
    empleado.setPrimerNombre(empleadoDTO.getPrimerNombre());
    empleado.setPrimerApellido(empleadoDTO.getPrimerApellido());
    empleado.setEmail(empleadoDTO.getEmail());
    empleado.setTelefono(empleadoDTO.getTelefono());
    System.out.println("LLEGA 2------------------------------------------------------------------");

    empleado.setRol(EmpleadoRol.valueOf(empleadoDTO.getRol()));

    empleado.setRoles(null);

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

    System.out.println("LLEGA 4------------------------------------------------------------------");

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