package com.moufflet.ecommerce_backend.ciudad.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.moufflet.ecommerce_backend.direccion.model.Direccion;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@ToString(exclude = "direccion")
public class Ciudad {

  @Id
  @Column(length = 10)
  private String id;
  @Column(length = 30)
  private String nombre;

  @OneToMany
  @JsonManagedReference
  private List<Direccion> direccion;

}
