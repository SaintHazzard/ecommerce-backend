package com.moufflet.ecommerce_backend.producto.application;

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

}
