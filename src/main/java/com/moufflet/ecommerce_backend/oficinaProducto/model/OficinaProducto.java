package com.moufflet.ecommerce_backend.oficinaProducto.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OficinaProducto {

  @EmbeddedId
  private OficinaProductoId id;

  private Integer stock;
}
