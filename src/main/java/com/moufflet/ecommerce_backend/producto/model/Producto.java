package com.moufflet.ecommerce_backend.producto.model;

import java.math.BigDecimal;
import java.util.List;

import com.moufflet.ecommerce_backend.gama.model.Gama;
import com.moufflet.ecommerce_backend.pedido.model.PedidoProducto;
import com.moufflet.ecommerce_backend.proveedor.model.Proveedor;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(unique = true)
  private String codigo;
  @Column(length = 50)
  private String nombre;
  private String descripcion;
  @Column(precision = 15, scale = 2)
  private BigDecimal precio;

  @ManyToOne
  private Gama gama;

  @ManyToOne
  private Proveedor proveedor;

  @Lob
  @Column(name = "imagen", columnDefinition = "BLOB")
  private byte[] imagen;

  private boolean estado;

  @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
  private List<PedidoProducto> pedidos;
}
