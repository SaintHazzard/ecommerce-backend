package com.moufflet.ecommerce_backend.cliente.infraestructure.adapter.out;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moufflet.ecommerce_backend.cliente.application.ClienteRepositoryPort;
import com.moufflet.ecommerce_backend.cliente.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, String>,ClienteRepositoryPort {
  
}
