package com.moufflet.ecommerce_backend.formaPagoTercero.application;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moufflet.ecommerce_backend.Formapago.application.FormaPagoService;
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

  public FormaPagoTerceroDTO guardarFormaPagoTercero(FormaPagoTerceroDTO formaPagoTerceroDTO) {
    FormaPagoTercero formaPagoTercero = DTOtoEntity(formaPagoTerceroDTO);
    formaPagoTercero = formaPagoTerceroRepositoryPort.save(formaPagoTercero);
    return entityToDTO(formaPagoTercero);
  }

  public FormaPagoTerceroDTO buscarFormaPagoTerceroPorId(Long pedidoId, String terceroId) {
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
        .build();
  }

  public FormaPagoTerceroDTO entityToDTO(FormaPagoTercero formaPagoTercero) {
    return FormaPagoTerceroDTO.builder()
        .id(formaPagoTercero.getId().getFormaPagoId())
        .formaPagoId(formaPagoTercero.getId().getFormaPagoId())
        .terceroId(formaPagoTercero.getId().getTerceroId())
        .fechaPago(formaPagoTercero.getFechaPago())
        .build();
  }
}
