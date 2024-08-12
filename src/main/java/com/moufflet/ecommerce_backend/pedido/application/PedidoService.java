package com.moufflet.ecommerce_backend.pedido.application;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.moufflet.ecommerce_backend.formaPagoTercero.application.FormaPagoTerceroService;
import com.moufflet.ecommerce_backend.formaPagoTercero.model.FormaPagoTercero;
import com.moufflet.ecommerce_backend.formaPagoTercero.model.FormaPagoTerceroId;
import com.moufflet.ecommerce_backend.pedido.model.EstadoPedido;
import com.moufflet.ecommerce_backend.pedido.model.Pedido;
import com.moufflet.ecommerce_backend.pedido.model.PedidoProducto;
import com.moufflet.ecommerce_backend.pedido.model.PedidoProductoDTO;
import com.moufflet.ecommerce_backend.pedido.model.PedidoProductoId;
import com.moufflet.ecommerce_backend.producto.application.port.ProductoRepositoryPort;
import com.moufflet.ecommerce_backend.producto.model.Producto;
import com.moufflet.ecommerce_backend.tercero.application.TerceroService;

import jakarta.transaction.Transactional;

@Service
public class PedidoService {

  @Autowired
  private PedidoRepositoryPort pedidoRepository;

  @Autowired
  private ProductoRepositoryPort productoRepository;

  @Autowired
  private PedidoProductoRepositoryPort pedidoProductoRepository;

  @Autowired
  private TerceroService terceroService;

  @Value("${spring.datasource.url}")
  private String url;
  @Value("${spring.datasource.username}")
  private String username;
  @Value("${spring.datasource.password}")
  private String password;

  @Autowired
  private FormaPagoTerceroService formaPagoService;

  @Transactional
  public Pedido createPedido(PedidoDTO pedidoDTO, List<PedidoProductoDTO> productosDTO) {
    Pedido pedidoEntity = DTOtoEntity(pedidoDTO);
    pedidoEntity.setTotal(productosDTO.stream()
        .map(pedidoProductoDTO -> productoRepository.findById(pedidoProductoDTO.getId().getProducto()).orElse(null)
            .getPrecio()
            .multiply(BigDecimal.valueOf(pedidoProductoDTO.getCantidad())))
        .reduce(BigDecimal.ZERO, BigDecimal::add));
    Pedido savedPedido = pedidoRepository.save(pedidoEntity);
    System.out.println("Pedido guardado: " + savedPedido);
    for (PedidoProductoDTO pedidoProductoDTO : productosDTO) {
      PedidoProducto pedidoProductoEntity = DTOtoEntity(pedidoProductoDTO, savedPedido);
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

  public Pedido updatePedido(Long id, PedidoDTO updatedPedido) {

    System.out.println("Pedido a actualizar: " + updatedPedido);
    Optional<Pedido> existingPedido = pedidoRepository.findById(id);
    FormaPagoTercero formaPagoTercero = formaPagoService
        .getById(new FormaPagoTerceroId(Long.valueOf(updatedPedido.getFormaPagoId()), updatedPedido.getTerceroId()));
    if (existingPedido.isPresent()) {
      Pedido pedido = existingPedido.get();
      pedido.setFechaPedido(updatedPedido.getFechaPedido());
      pedido.setFechaEsperada(updatedPedido.getFechaEsperada());
      pedido.setFechaEntrega(updatedPedido.getFechaEntrega());
      pedido.setEstado(EstadoPedido.valueOf(updatedPedido.getEstado()));
      pedido.setComentarios(updatedPedido.getComentarios());
      pedido.setFormaPagoTercero(formaPagoTercero);
      try (Connection conn = DriverManager.getConnection(url, username, password)) {
        String query = "UPDATE pedido SET forma_pago_tercero_forma_pago_id = ?, forma_pago_tercero_tercero_id =? WHERE id = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setLong(1, Long.parseLong(updatedPedido.getFormaPagoId()));
        preparedStatement.setString(2, updatedPedido.getTerceroId());
        preparedStatement.setLong(3, id);
        preparedStatement.executeUpdate();
      } catch (SQLException e) {
        e.printStackTrace();
      }
      return pedidoRepository.save(pedido);
    } else

    {
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

  public List<PedidoDTO> getAllPedidosByState(String estado) {
    EstadoPedido estadoPedido = EstadoPedido.valueOf(estado);
    return pedidoRepository.findByEstado(
        estadoPedido).stream().map(pedido -> entityToDTO(pedido))
        .toList();
  }

  private Pedido DTOtoEntity(PedidoDTO pedidoDTO) {
    return Pedido.builder()
        .fechaPedido(pedidoDTO.getFechaPedido())
        .fechaEsperada(pedidoDTO.getFechaEsperada())
        .fechaEntrega(pedidoDTO.getFechaEntrega())
        .estado(EstadoPedido.valueOf(pedidoDTO.getEstado()))
        .comentarios(pedidoDTO.getComentarios())
        .build();
  }

  public PedidoDTO entityToDTO(Pedido pedido) {
    return PedidoDTO.builder()
        .id(pedido.getId())
        .fechaPedido(pedido.getFechaPedido())
        .fechaEsperada(pedido.getFechaEsperada())
        .fechaEntrega(pedido.getFechaEntrega())
        .estado(pedido.getEstado().name())
        .comentarios(pedido.getComentarios())
        .build();
  }

  private PedidoProducto DTOtoEntity(PedidoProductoDTO pedidoProductoDTO, Pedido pedido) {
    return PedidoProducto.builder()
        .cantidad(pedidoProductoDTO.getCantidad())
        .pedido(pedido)
        .producto(productoRepository.findById(pedidoProductoDTO.getId().getProducto())
            .orElseThrow(() -> new RuntimeException("Producto no encontrado")))
        .id(new PedidoProductoId(pedido.getId(), pedidoProductoDTO.getId().getProducto()))
        .build();
  }

  public List<PedidoDTO> getAllPedidosByEmpleado(String empleadoId) {
    List<Pedido> pedidosEmpleado = pedidoRepository.findByEmpleadoId(empleadoId);
    return pedidosEmpleado.stream().map(this::entityToDTO).toList();
  }

}