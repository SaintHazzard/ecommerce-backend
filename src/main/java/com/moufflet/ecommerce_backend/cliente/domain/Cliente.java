package com.moufflet.ecommerce_backend.cliente.domain;

import java.math.BigDecimal;
import java.util.List;

import com.moufflet.ecommerce_backend.direccion.model.Direccion;
import com.moufflet.ecommerce_backend.tercero.domain.Tercero;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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

  @ManyToMany
  @JoinTable(name = "cliente_direccion", joinColumns = @JoinColumn(name = "cliente_id"), inverseJoinColumns = @JoinColumn(name = "direccion_id"))
  private List<Direccion> direccion;
  @Column(precision = 15, scale = 2)
  private BigDecimal credito;

}
