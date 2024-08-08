package com.moufflet.ecommerce_backend.pedido.application;

import com.moufflet.ecommerce_backend.pedido.model.Pedido;
import com.moufflet.ecommerce_backend.pedido.model.PedidoProducto;
import com.moufflet.ecommerce_backend.pedido.model.PedidoProductoId;

import com.moufflet.ecommerce_backend.producto.model.Producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.moufflet.ecommerce_backend.producto.application.port.ProductoRepositoryPort;

@Service
public class PedidoService {

  @Autowired
  private PedidoRepositoryPort pedidoRepository;

  @Autowired
  private ProductoRepositoryPort productoRepository;

  @Autowired
  private PedidoProductoRepositoryPort pedidoProductoRepository;

  public Pedido createPedido(Pedido pedido, List<PedidoProducto> productos) {
    Pedido savedPedido = pedidoRepository.save(pedido);
    for (PedidoProducto pedidoProducto : productos) {
      pedidoProducto.setPedido(savedPedido);
      pedidoProducto.setProducto(productoRepository.findById(pedidoProducto.getProducto().getId())
          .orElseThrow(() -> new RuntimeException("Producto no encontrado")));
      pedidoProducto.setId(new PedidoProductoId(savedPedido.getId(), pedidoProducto.getProducto().getId()));
      pedidoProductoRepository.save(pedidoProducto);
    }
    return savedPedido;
  }

  public List<Pedido> getAllPedidos() {
    return pedidoRepository.findAll();
  }

  public Optional<Pedido> getPedidoById(Long id) {
    return pedidoRepository.findById(id);
  }

  public void deletePedido(Long id) {
    pedidoRepository.deleteById(id);
  }

  public Pedido updatePedido(Long id, Pedido updatedPedido) {
    Optional<Pedido> existingPedido = pedidoRepository.findById(id);
    if (existingPedido.isPresent()) {
      Pedido pedido = existingPedido.get();
      pedido.setFechaPedido(updatedPedido.getFechaPedido());
      pedido.setFechaEsperada(updatedPedido.getFechaEsperada());
      pedido.setFechaEntrega(updatedPedido.getFechaEntrega());
      pedido.setEstado(updatedPedido.getEstado());
      pedido.setComentarios(updatedPedido.getComentarios());
      return pedidoRepository.save(pedido);
    } else {
      throw new RuntimeException("Pedido no encontrado");
    }
  }

  public PedidoProducto addProductoToPedido(Long pedidoId, Long productoId, int cantidad, BigDecimal precio) {
    Pedido pedido = pedidoRepository.findById(pedidoId).orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
    Producto producto = productoRepository.findById(productoId)
        .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

    PedidoProductoId pedidoProductoId = new PedidoProductoId(pedidoId, productoId);
    PedidoProducto pedidoProducto = new PedidoProducto(pedidoProductoId, pedido, producto, cantidad, precio);

    return pedidoProductoRepository.save(pedidoProducto);
  }
}