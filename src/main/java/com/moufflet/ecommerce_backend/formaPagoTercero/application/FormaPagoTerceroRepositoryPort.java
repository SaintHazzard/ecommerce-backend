package com.moufflet.ecommerce_backend.formaPagoTercero.application;

import java.util.List;
import java.util.Optional;

import com.moufflet.ecommerce_backend.formaPagoTercero.model.FormaPagoTercero;
import com.moufflet.ecommerce_backend.formaPagoTercero.model.FormaPagoTerceroId;

public interface FormaPagoTerceroRepositoryPort {
  FormaPagoTercero save(FormaPagoTercero formaPagoTercero);

  Optional<FormaPagoTercero> findById(FormaPagoTerceroId id);

  void deleteById(FormaPagoTerceroId id);

  List<FormaPagoTercero> findByFormaPagoId(Long formaPagoId);

  List<FormaPagoTercero> findByTerceroId(String terceroId);
}
