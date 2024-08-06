package com.moufflet.ecommerce_backend.direccion.infraestructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moufflet.ecommerce_backend.direccion.application.DireccionRepositoryPort;
import com.moufflet.ecommerce_backend.direccion.model.Direccion;

@Repository
public interface DireccionRepository extends JpaRepository<Direccion, Long>, DireccionRepositoryPort {

}
