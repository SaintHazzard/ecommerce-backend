package com.moufflet.ecommerce_backend.Formapago.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moufflet.ecommerce_backend.Formapago.model.FormaPago;
import com.moufflet.ecommerce_backend.Formapago.model.FormaPagoDTO;

@Service
public class FormaPagoService {

  @Autowired
  private FormaPagoRepositoryPort formaPagoRepositoryPort;

  public FormaPago guardarFormaPago(FormaPagoDTO formaPago) {
    return formaPagoRepositoryPort.save(DTOtoEntity(formaPago));
  }

  public List<FormaPagoDTO> getAll() {
    return formaPagoRepositoryPort.findAll().stream().map(fp -> entityToDTO(fp)).toList();
  }

  public FormaPago buscarFormaPagoPorId(Long id) {
    return formaPagoRepositoryPort.findById(id).orElse(null);
  }

  public void eliminarFormaPagoPorId(Long id) {
    formaPagoRepositoryPort.deleteById(id);
  }

  public FormaPago buscarFormaPagoPorNombre(String nombre) {
    return formaPagoRepositoryPort.findByNombre(nombre).orElse(null);
  }

  public FormaPagoDTO entityToDTO(FormaPago formaPago) {
    return FormaPagoDTO.builder()
        .id(formaPago.getId())
        .nombre(formaPago.getNombre())
        .build();
  }

  public FormaPago DTOtoEntity(FormaPagoDTO formaPagoDTO) {
    return FormaPago.builder()
        .id(formaPagoDTO.getId())
        .nombre(formaPagoDTO.getNombre())
        .build();
  }

  public FormaPagoDTO actualizarFormaPago(FormaPagoDTO formaPagoDTO) {
    FormaPago formaPago = DTOtoEntity(formaPagoDTO);
    formaPago = formaPagoRepositoryPort.save(formaPago);
    return entityToDTO(formaPago);
  }

}
