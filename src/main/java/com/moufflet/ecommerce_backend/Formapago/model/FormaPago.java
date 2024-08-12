package com.moufflet.ecommerce_backend.Formapago.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
import lombok.ToString;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "formaPagoTerceros")
public class FormaPago {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nombre;
  @OneToMany(mappedBy = "formaPago")
  @JsonManagedReference
  private List<FormaPagoTercero> formaPagoTerceros;
}
