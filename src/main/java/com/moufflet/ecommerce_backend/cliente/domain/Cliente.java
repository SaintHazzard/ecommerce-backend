package com.moufflet.ecommerce_backend.cliente.domain;

import java.math.BigDecimal;

import com.moufflet.ecommerce_backend.direccion.model.Direccion;
import com.moufflet.ecommerce_backend.tercero.domain.Tercero;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente extends Tercero {
  private String preferecias;
  @OneToOne
  private Direccion direccion;
  @Column(precision = 15, scale = 2)
  private BigDecimal credito;

  public Cliente(String id) {
    super(id);
  }
}
