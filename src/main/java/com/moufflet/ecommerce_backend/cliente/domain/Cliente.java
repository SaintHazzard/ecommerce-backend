package com.moufflet.ecommerce_backend.cliente.domain;

import java.math.BigDecimal;

import com.moufflet.ecommerce_backend.tercero.domain.Tercero;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper = false)
@Data
public class Cliente extends Tercero {
  private String preferecias;
  private String direccion;
  @Column(precision = 15, scale = 2)
  private BigDecimal credito;
}
