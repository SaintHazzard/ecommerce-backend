package com.moufflet.ecommerce_backend.formaPagoTercero.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.moufflet.ecommerce_backend.Formapago.model.FormaPago;
import com.moufflet.ecommerce_backend.empleado.model.Empleado;
import com.moufflet.ecommerce_backend.pedido.model.Pedido;
import com.moufflet.ecommerce_backend.tercero.domain.Tercero;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
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
@ToString(exclude = "pedidos")
public class FormaPagoTercero {
  @EmbeddedId
  private FormaPagoTerceroId id;
  @MapsId("formaPagoId")
  @ManyToOne(fetch = FetchType.EAGER)
  @JsonBackReference
  private FormaPago formaPago;
  @MapsId("terceroId")
  @ManyToOne(fetch = FetchType.EAGER)
  private Tercero tercero;

  @OneToMany(mappedBy = "formaPagoTercero")
  private List<Pedido> pedidos;

  @ManyToOne
  private Empleado empleado;

  private LocalDate fechaPago;
}
