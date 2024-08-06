package com.moufflet.ecommerce_backend.ciudad.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ciudad {

  @Id
  @JsonProperty("cod_mpio")
  @Column(length = 10)
  private String codigo;
  @Column(length = 30)
  @JsonProperty("nom_mpio")
  private String nombre;

}
