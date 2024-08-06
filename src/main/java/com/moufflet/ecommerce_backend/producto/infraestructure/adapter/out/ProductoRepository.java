package com.moufflet.ecommerce_backend.producto.infraestructure.adapter.out;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moufflet.ecommerce_backend.producto.application.port.ProductoRepositoryPort;
import com.moufflet.ecommerce_backend.producto.model.Producto;

@Repository
public interface ProductoRepository extends  JpaRepository<Producto, Long>,ProductoRepositoryPort {
  
}
