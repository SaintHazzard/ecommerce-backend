package com.moufflet.ecommerce_backend.formaPagoTercero.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FormaPagoTerceroId implements Serializable {
  private Long formaPagoId;
  private String terceroId;
}
