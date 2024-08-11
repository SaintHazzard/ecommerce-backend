package com.moufflet.ecommerce_backend.Formapago.infraestructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moufflet.ecommerce_backend.Formapago.application.FormaPagoRepositoryPort;
import com.moufflet.ecommerce_backend.Formapago.model.FormaPago;

@Repository
public interface FormaPagoRepository extends JpaRepository<FormaPago, Long>,FormaPagoRepositoryPort {
  

}
