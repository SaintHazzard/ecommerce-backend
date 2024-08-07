package com.moufflet.ecommerce_backend.direccion.model;

import com.moufflet.ecommerce_backend.ciudad.model.Ciudad;
import com.moufflet.ecommerce_backend.cliente.domain.Cliente;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Direccion {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String tipoCalle;

  @Column(nullable = false)
  private String numeroCalle;

  @Column(nullable = false)
  private String numeroComplemento;

  @Column(nullable = false)
  private String codigoPostal;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private Ciudad ciudad;

  @OneToOne
  private Cliente cliente;

}
