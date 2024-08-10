package com.moufflet.ecommerce_backend.oficina.infraestructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moufflet.ecommerce_backend.oficina.application.OficinaRepositoryPort;
import com.moufflet.ecommerce_backend.oficina.model.Oficina;

@Repository
public interface OficinaRepository extends JpaRepository<Oficina, Long>, OficinaRepositoryPort {

}
