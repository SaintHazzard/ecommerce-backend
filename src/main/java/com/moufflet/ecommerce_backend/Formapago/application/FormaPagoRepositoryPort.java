package com.moufflet.ecommerce_backend.Formapago.application;

import java.util.Optional;

import com.moufflet.ecommerce_backend.Formapago.model.FormaPago;

public interface FormaPagoRepositoryPort {
  FormaPago save(FormaPago formaPago);

  Optional<FormaPago> findById(Long id);

  void deleteById(Long id);

  Optional<FormaPago> findByNombre(String nombre);
}