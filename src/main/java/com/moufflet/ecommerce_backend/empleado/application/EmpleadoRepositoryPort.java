package com.moufflet.ecommerce_backend.empleado.application;

import java.util.List;
import java.util.Optional;

import com.moufflet.ecommerce_backend.empleado.model.Empleado;

public interface EmpleadoRepositoryPort {
  Empleado save(Empleado empleado);

  Optional<Empleado> findById(String id);

  List<Empleado> findAll();

  List<Empleado> findAllByJefeNombre(String nombre);

  List<Empleado> findAllByJefeId(String nombre);

  void deleteById(String id);

  Empleado findByEmail(String email);

  Empleado findByTelefono(String telefono);

  Empleado findByOficinaId(Long oficinaId);

  List<Empleado> findByOficinaCiudadNombre(String ciudad);

  List<Empleado> findByOficinaNombre(String nombre);

  List<Empleado> findByRol(String rol);

}
