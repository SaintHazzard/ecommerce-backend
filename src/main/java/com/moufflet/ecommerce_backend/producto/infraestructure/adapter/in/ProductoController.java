package com.moufflet.ecommerce_backend.producto.infraestructure.adapter.in;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moufflet.ecommerce_backend.producto.application.ProductoService;
import com.moufflet.ecommerce_backend.producto.model.Producto;

@RequestMapping("/admin/producto")
@RestController
public class ProductoController {

  @Autowired
  private ProductoService productoService;

  @PostMapping("/crear")
  public ResponseEntity<Producto> createProduct(@RequestBody Producto producto) {
    Producto nuevoProducto = productoService.createProducto(producto);
    return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);
  }

  @GetMapping("/getAll")
  public List<Producto> getAllProducto() {
    return productoService.getAll();
  }

}
