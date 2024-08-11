package com.moufflet.ecommerce_backend.formaPagoTercero.infraestructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moufflet.ecommerce_backend.formaPagoTercero.model.FormaPagoTercero;
import com.moufflet.ecommerce_backend.formaPagoTercero.model.FormaPagoTerceroId;

@Repository
public interface FormaPagoTerceroRepository extends JpaRepository<FormaPagoTercero, FormaPagoTerceroId> {

}
