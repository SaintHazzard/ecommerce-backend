package com.moufflet.ecommerce_backend.proveedor.Infraestructure.adapter.out;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moufflet.ecommerce_backend.proveedor.application.port.ProveedorRepositoryPort;
import com.moufflet.ecommerce_backend.proveedor.model.Proveedor;


@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, String>,ProveedorRepositoryPort {

}