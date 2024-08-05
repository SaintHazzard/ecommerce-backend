package com.moufflet.ecommerce_backend.producto.infraestructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moufflet.ecommerce_backend.producto.application.port.ProductoRepositoryPort;
import com.moufflet.ecommerce_backend.producto.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long>,ProductoRepositoryPort {
  
}
