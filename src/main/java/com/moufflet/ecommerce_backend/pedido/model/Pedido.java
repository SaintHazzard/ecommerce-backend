package com.moufflet.ecommerce_backend.pedido.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.moufflet.ecommerce_backend.empleado.model.Empleado;
import com.moufflet.ecommerce_backend.formaPagoTercero.model.FormaPagoTercero;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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

  private BigDecimal total;

  @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
  @JsonIgnore
  private List<PedidoProducto> productos;

  @ManyToOne(cascade = { CascadeType.ALL, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
      CascadeType.DETACH })
  @OnDelete(action = OnDeleteAction.SET_NULL)
  private FormaPagoTercero formaPagoTercero;

  @ManyToOne(cascade = { CascadeType.ALL, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
      CascadeType.DETACH })
  @OnDelete(action = OnDeleteAction.SET_NULL)
  private Empleado empleado;
}
