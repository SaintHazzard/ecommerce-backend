package com.moufflet.ecommerce_backend.pedido.application;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moufflet.ecommerce_backend.pedido.model.Pedido;
import com.moufflet.ecommerce_backend.pedido.model.PedidoProducto;
import com.moufflet.ecommerce_backend.pedido.model.PedidoProductoDTO;
import com.moufflet.ecommerce_backend.pedido.model.PedidoProductoId;
import com.moufflet.ecommerce_backend.producto.application.port.ProductoRepositoryPort;
import com.moufflet.ecommerce_backend.producto.model.Producto;

import jakarta.transaction.Transactional;

@Service
public class PedidoService {

  @Autowired
  private PedidoRepositoryPort pedidoRepository;

  @Autowired
  private ProductoRepositoryPort productoRepository;

  @Autowired
  private PedidoProductoRepositoryPort pedidoProductoRepository;

  @Transactional
  public Pedido createPedido(PedidoDTO pedidoDTO, List<PedidoProductoDTO> productosDTO) {
    Pedido pedidoEntity = mapPedidoDTOToPedido(pedidoDTO);
    Pedido savedPedido = pedidoRepository.save(pedidoEntity);
    System.out.println("Pedido guardado: " + savedPedido);

    for (PedidoProductoDTO pedidoProductoDTO : productosDTO) {
      PedidoProducto pedidoProductoEntity = mapPedidoProductoDTOToPedidoProducto(pedidoProductoDTO, savedPedido);
      pedidoProductoRepository.save(pedidoProductoEntity);
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
    PedidoProducto pedidoProducto = new PedidoProducto(pedidoProductoId, pedido, producto, cantidad);

    return pedidoProductoRepository.save(pedidoProducto);
  }

  public List<Pedido> getAllPedidosByState(String estado) {
    return pedidoRepository.findByEstado(estado);
  }

  private Pedido mapPedidoDTOToPedido(PedidoDTO pedidoDTO) {
    return Pedido.builder()
        .fechaPedido(pedidoDTO.getFechaPedido())
        .fechaEsperada(pedidoDTO.getFechaEsperada())
        .fechaEntrega(pedidoDTO.getFechaEntrega())
        .estado(pedidoDTO.getEstado())
        .comentarios(pedidoDTO.getComentarios())
        .build();
  }

  private PedidoProducto mapPedidoProductoDTOToPedidoProducto(PedidoProductoDTO pedidoProductoDTO, Pedido pedido) {
    return PedidoProducto.builder()
        .cantidad(pedidoProductoDTO.getCantidad())
        .pedido(pedido)
        .producto(productoRepository.findById(pedidoProductoDTO.getId().getProducto())
            .orElseThrow(() -> new RuntimeException("Producto no encontrado")))
        .id(new PedidoProductoId(pedido.getId(), pedidoProductoDTO.getId().getProducto()))
        .build();
  }

  
}