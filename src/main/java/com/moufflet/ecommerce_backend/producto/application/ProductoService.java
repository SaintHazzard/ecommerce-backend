package com.moufflet.ecommerce_backend.producto.application;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.moufflet.ecommerce_backend.gama.application.GamaService;
import com.moufflet.ecommerce_backend.gama.model.Gama;
import com.moufflet.ecommerce_backend.producto.application.port.ProductoRepositoryPort;
import com.moufflet.ecommerce_backend.producto.model.Producto;
import com.moufflet.ecommerce_backend.producto.model.ProductoDTO;
import com.moufflet.ecommerce_backend.proveedor.application.ProveedorService;
import com.moufflet.ecommerce_backend.proveedor.model.Proveedor;

import io.jsonwebtoken.io.IOException;

@Service
public class ProductoService {

  @Autowired
  private ProductoRepositoryPort productoRepositoryPort;

  @Autowired
  private GamaService gamaService;

  @Autowired
  private ProveedorService proveedorService;

  public ResponseEntity<Producto> createProducto(
      @RequestPart("producto") ProductoDTO productoDTO,
      @RequestPart("imagen") MultipartFile imagen) throws java.io.IOException {
    try {
      Producto producto = Producto.builder()
          .codigo(productoDTO.getCodigo())
          .nombre(productoDTO.getNombre())
          .descripcion(productoDTO.getDescripcion())
          .precio(productoDTO.getPrecio())
          .gama(gamaService.findById(productoDTO.getGamaId()))
          .proveedor(proveedorService.findById(productoDTO.getProveedorId()))
          .imagen(imagen.getBytes())
          .estado(productoDTO.isEstado())
          .build();
      Producto savedProducto = productoRepositoryPort.save(producto);
      return new ResponseEntity<>(savedProducto, HttpStatus.CREATED);
    } catch (IOException e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  public Producto getById(Long id) {
    return productoRepositoryPort.findById(id).orElse(null);
  }

  public void deleteById(Long id) {
    productoRepositoryPort.deleteById(id);
  }

  public List<ProductoDTO> getAll() {
    return productoRepositoryPort.findAll().stream()
        .map(this::convertToDTO)
        .collect(Collectors.toList());
  }

  private ProductoDTO convertToDTO(Producto producto) {
    ProductoDTO dto = new ProductoDTO();
    dto.setId(producto.getId());
    dto.setCodigo(producto.getCodigo());
    dto.setNombre(producto.getNombre());
    dto.setDescripcion(producto.getDescripcion());
    dto.setPrecio(producto.getPrecio());
    dto.setGamaId(producto.getGama().getId());
    dto.setProveedorId(producto.getProveedor().getId());
    dto.setEstado(producto.isEstado());
    dto.setImagen(producto.getImagen());
    return dto;
  }

  public Producto updateProducto(Long id, ProductoDTO productoDTO, byte[] imagen) {
    Producto producto = getById(id);

    producto.setCodigo(productoDTO.getCodigo());
    producto.setNombre(productoDTO.getNombre());
    producto.setDescripcion(productoDTO.getDescripcion());
    producto.setPrecio(productoDTO.getPrecio());
    if (productoDTO.getGamaId() != null) {
      Gama gama = gamaService.findById(productoDTO.getGamaId());
      producto.setGama(gama);
    }
    if (productoDTO.getProveedorId() != null) {
      Proveedor proveedor = proveedorService.findById(productoDTO.getProveedorId());
      producto.setProveedor(proveedor);
    }
    producto.setEstado(productoDTO.isEstado());

    if (imagen != null && imagen.length > 0) {
      producto.setImagen(imagen);
    }

    return productoRepositoryPort.save(producto);
  }

}
