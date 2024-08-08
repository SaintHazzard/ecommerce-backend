package com.moufflet.ecommerce_backend.pedido.infraestructure;

import com.moufflet.ecommerce_backend.pedido.application.PedidoRequest;
import com.moufflet.ecommerce_backend.pedido.application.PedidoService;
import com.moufflet.ecommerce_backend.pedido.model.Pedido;
import com.moufflet.ecommerce_backend.pedido.model.PedidoProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/pedido")
public class PedidoController {

  @Autowired
  private PedidoService pedidoService;

  @PostMapping("/crear")
  public ResponseEntity<Pedido> createPedido(@RequestBody PedidoRequest pedidoRequest) {
    Pedido savedPedido = pedidoService.createPedido(pedidoRequest.getPedido(), pedidoRequest.getProductos());
    return new ResponseEntity<>(savedPedido, HttpStatus.CREATED);
  }

  @GetMapping("/getAll")
  public ResponseEntity<List<Pedido>> getAllPedidos() {
    List<Pedido> pedidos = pedidoService.getAllPedidos();
    return new ResponseEntity<>(pedidos, HttpStatus.OK);
  }

  @GetMapping("/get/{id}")
  public ResponseEntity<Pedido> getPedidoById(@PathVariable Long id) {
    Optional<Pedido> pedido = pedidoService.getPedidoById(id);
    return pedido.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<Pedido> updatePedido(@PathVariable Long id, @RequestBody Pedido pedido) {
    Pedido updatedPedido = pedidoService.updatePedido(id, pedido);
    return new ResponseEntity<>(updatedPedido, HttpStatus.OK);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Void> deletePedido(@PathVariable Long id) {
    pedidoService.deletePedido(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PostMapping("/addProducto")
  public ResponseEntity<PedidoProducto> addProductoToPedido(@RequestParam Long pedidoId, @RequestParam Long productoId,
      @RequestParam int cantidad, @RequestParam BigDecimal precio) {
    PedidoProducto pedidoProducto = pedidoService.addProductoToPedido(pedidoId, productoId, cantidad, precio);
    return new ResponseEntity<>(pedidoProducto, HttpStatus.CREATED);
  }

  @GetMapping("/getAllByState")
  public ResponseEntity<List<Pedido>> getAllPedidosByState(@RequestParam String estado) {
    List<Pedido> pedidos = pedidoService.getAllPedidosByState(estado.toLowerCase());
    return new ResponseEntity<>(pedidos, HttpStatus.OK);
  }
}
