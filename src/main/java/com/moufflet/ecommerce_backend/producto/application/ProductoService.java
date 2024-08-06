package com.moufflet.ecommerce_backend.producto.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moufflet.ecommerce_backend.producto.application.port.ProductoRepositoryPort;
import com.moufflet.ecommerce_backend.producto.model.Producto;

@Service
public class ProductoService {

  @Autowired
  private ProductoRepositoryPort productoRepositoryPort;

  public Producto createProducto(Producto producto) {
    return productoRepositoryPort.save(producto);
  }

  public Producto findById(Long id) {
    return productoRepositoryPort.findById(id).orElse(null);
  }

  public void deleteById(Long id) {
    productoRepositoryPort.deleteById(id);
  }

  public List<Producto> getAll() {
    return productoRepositoryPort.findAll();
  }

}
