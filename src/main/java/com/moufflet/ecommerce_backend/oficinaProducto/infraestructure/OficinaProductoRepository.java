package com.moufflet.ecommerce_backend.oficinaProducto.infraestructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moufflet.ecommerce_backend.oficinaProducto.application.OficinaProductoRepositoryPort;
import com.moufflet.ecommerce_backend.oficinaProducto.model.OficinaProducto;
import com.moufflet.ecommerce_backend.oficinaProducto.model.OficinaProductoId;

@Repository
public interface OficinaProductoRepository
    extends JpaRepository<OficinaProducto, OficinaProductoId>, OficinaProductoRepositoryPort {

}
