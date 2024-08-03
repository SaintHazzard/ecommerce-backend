package com.moufflet.ecommerce_backend.tercero.infraestructure.adapter.out;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moufflet.ecommerce_backend.tercero.application.port.out.TerceroRepositoryPort;
import com.moufflet.ecommerce_backend.tercero.domain.Tercero;

@Repository
public interface TerceroRepository extends JpaRepository<Tercero, String>, TerceroRepositoryPort {
}
