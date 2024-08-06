package com.moufflet.ecommerce_backend.ciudad.infraestructure.adapter.out;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moufflet.ecommerce_backend.ciudad.application.port.CiudadRepositoryPort;
import com.moufflet.ecommerce_backend.ciudad.model.Ciudad;

@Repository
public interface CiudadRepository extends JpaRepository<Ciudad, String>, CiudadRepositoryPort {

}
