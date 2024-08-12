package com.moufflet.ecommerce_backend.formaPagoTercero.application;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moufflet.ecommerce_backend.Formapago.application.FormaPagoService;
import com.moufflet.ecommerce_backend.empleado.application.EmpleadoService;
import com.moufflet.ecommerce_backend.formaPagoTercero.model.FormaPagoTercero;
import com.moufflet.ecommerce_backend.formaPagoTercero.model.FormaPagoTerceroDTO;
import com.moufflet.ecommerce_backend.formaPagoTercero.model.FormaPagoTerceroId;
import com.moufflet.ecommerce_backend.tercero.application.TerceroService;

@Service
public class FormaPagoTerceroService {

  @Autowired
  private FormaPagoTerceroRepositoryPort formaPagoTerceroRepositoryPort;

  @Autowired
  private FormaPagoService formaPagoService;

  @Autowired
  private TerceroService terceroService;

  @Autowired
  private EmpleadoService empleadoService;

  public FormaPagoTerceroDTO guardarFormaPagoTercero(FormaPagoTerceroDTO formaPagoTerceroDTO) {
    FormaPagoTercero formaPagoTercero = DTOtoEntity(formaPagoTerceroDTO);
    System.out.println("------------------------------------------------------------------------------");
    System.out.println("Forma de pago tercero: " + formaPagoTercero);
    System.out.println("------------------------------------------------------------------------------");
    formaPagoTercero = formaPagoTerceroRepositoryPort.save(formaPagoTercero);
    return entityToDTO(formaPagoTercero);
  }

  public FormaPagoTercero DTOtoEntity(FormaPagoTerceroDTO formaPagoTerceroDTO) {
    FormaPagoTerceroId id = FormaPagoTerceroId.builder()
        .formaPagoId(formaPagoTerceroDTO.getFormaPagoId())
        .terceroId(formaPagoTerceroDTO.getTerceroId())
        .build();
    return FormaPagoTercero.builder()
        .id(id)
        .formaPago(formaPagoService.buscarFormaPagoPorId((formaPagoTerceroDTO.getFormaPagoId())))
        .tercero(terceroService.getById(formaPagoTerceroDTO
            .getTerceroId()))
        .fechaPago(formaPagoTerceroDTO.getFechaPago())
        .empleado(empleadoService.getById(formaPagoTerceroDTO.getEmpleadoId()))
        .build();
  }

  public FormaPagoTerceroDTO entityToDTO(FormaPagoTercero formaPagoTercero) {
    return FormaPagoTerceroDTO.builder()
        .id(formaPagoTercero.getId().getFormaPagoId())
        .nombre(formaPagoTercero.getTercero().getPrimerNombre())
        .apellido(formaPagoTercero.getTercero().getPrimerApellido())
        .formaPagoNombre(formaPagoTercero.getFormaPago().getNombre())
        .formaPagoId(formaPagoTercero.getId().getFormaPagoId())
        .terceroId(formaPagoTercero.getId().getTerceroId())
        .fechaPago(formaPagoTercero.getFechaPago())
        .build();
  }

  public FormaPagoTercero getById(FormaPagoTerceroId id) {
    return formaPagoTerceroRepositoryPort.findById(id).orElse(null);
  }

  public List<FormaPagoTerceroDTO> getAll() {
    List<FormaPagoTercero> formaPagoTerceros = formaPagoTerceroRepositoryPort.findAll();
    return formaPagoTerceros.stream()
        .map(this::entityToDTO)
        .collect(Collectors.toList());
  }

  public FormaPagoTerceroDTO buscarFormaPagoTerceroAndPedidoPorId(Long pedidoId, String terceroId) {
    FormaPagoTerceroId id = FormaPagoTerceroId.builder()
        .formaPagoId(pedidoId)
        .terceroId(terceroId)
        .build();
    return formaPagoTerceroRepositoryPort.findById(id)
        .map(this::entityToDTO)
        .orElse(null);
  }

  public List<FormaPagoTerceroDTO> buscarFormaPagoTerceroPorFormaPagoId(Long formaPagoId) {
    return formaPagoTerceroRepositoryPort.findByFormaPagoId(formaPagoId).stream()
        .map(this::entityToDTO)
        .collect(Collectors.toList());
  }

  public List<FormaPagoTerceroDTO> buscarFormaPagoTerceroPorTerceroId(String terceroId) {
    return formaPagoTerceroRepositoryPort.findByTerceroId(terceroId).stream()
        .map(this::entityToDTO)
        .collect(Collectors.toList());
  }

}
