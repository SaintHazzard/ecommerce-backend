package com.moufflet.ecommerce_backend.direccion.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.moufflet.ecommerce_backend.ciudad.model.Ciudad;

import jakarta.persistence.CascadeType;
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
import lombok.ToString;

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

  @JoinColumn(name = "ciudad_id", nullable = false)
  @ManyToOne(cascade = { CascadeType.ALL, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
      CascadeType.DETACH })
  @OnDelete(action = OnDeleteAction.SET_NULL)
  @JsonManagedReference
  private Ciudad ciudad;
}
