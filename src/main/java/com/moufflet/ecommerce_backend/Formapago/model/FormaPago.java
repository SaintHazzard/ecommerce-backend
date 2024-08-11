package com.moufflet.ecommerce_backend.Formapago.model;

import java.util.List;

import com.moufflet.ecommerce_backend.formaPagoTercero.model.FormaPagoTercero;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormaPago {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nombre;
  @OneToMany(mappedBy = "formaPago")
  private List<FormaPagoTercero> formaPagoTerceros;
}
