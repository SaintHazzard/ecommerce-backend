package com.moufflet.ecommerce_backend.empleado.infraestructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moufflet.ecommerce_backend.empleado.application.EmpleadoRepositoryPort;
import com.moufflet.ecommerce_backend.empleado.model.Empleado;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, String>, EmpleadoRepositoryPort {

}
