package com.moufflet.ecommerce_backend.direccion.model;

import com.moufflet.ecommerce_backend.ciudad.model.Ciudad;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Direccion {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String tipoCalle;

  private String nombreCalle;

  private String numeroCalle;

  private String numeroComplemento;

  private String codigoPostal;

  @ManyToOne
  @JoinColumn(name = "ciudad_id", nullable = false)
  private Ciudad ciudad;

}
