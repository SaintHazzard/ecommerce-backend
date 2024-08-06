package com.moufflet.ecommerce_backend.gama.infraestructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moufflet.ecommerce_backend.gama.model.Gama;

import com.moufflet.ecommerce_backend.gama.application.port.GamaRepositoryPort;


@Repository
public interface GamaRepository extends JpaRepository<Gama, Long>, GamaRepositoryPort {
  
}
