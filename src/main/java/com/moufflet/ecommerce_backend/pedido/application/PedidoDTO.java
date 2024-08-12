package com.moufflet.ecommerce_backend.pedido.application;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PedidoDTO {
  private Long id;
  private Date fechaPedido;
  private Date fechaEsperada;

  private Date fechaEntrega;

  private String estado;

  private String comentarios;
  private String cliente;
  private String formaPagoId;
  private String terceroId;
  private BigDecimal total;

}
