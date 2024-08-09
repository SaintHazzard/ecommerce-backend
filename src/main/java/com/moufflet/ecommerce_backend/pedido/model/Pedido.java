package com.moufflet.ecommerce_backend.pedido.model;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class Pedido {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Date fechaPedido;

  private Date fechaEsperada;

  private Date fechaEntrega;

  @Enumerated(EnumType.STRING)
  private EstadoPedido estado;

  private String comentarios;

  @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
  @JsonIgnore
  private List<PedidoProducto> productos;
}
