package com.moufflet.ecommerce_backend.oficinaProducto.application;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moufflet.ecommerce_backend.oficina.application.OficinaService;
import com.moufflet.ecommerce_backend.oficina.model.Oficina;
import com.moufflet.ecommerce_backend.oficinaProducto.model.OficinaProducto;
import com.moufflet.ecommerce_backend.oficinaProducto.model.OficinaProductoDTO;
import com.moufflet.ecommerce_backend.oficinaProducto.model.OficinaProductoId;
import com.moufflet.ecommerce_backend.producto.application.ProductoService;
import com.moufflet.ecommerce_backend.producto.model.Producto;

@Service
public class OficinaProductoService {

  @Autowired
  private OficinaProductoRepositoryPort oficinaProductoRepositoryPort;

  @Autowired
  private ProductoService productoService;

  @Autowired
  private OficinaService oficinaService;

  public OficinaProductoDTO convertToDTO(OficinaProducto oficinaProducto) {
    return OficinaProductoDTO.builder()
        .oficinaId(oficinaProducto.getId().getOficina().getId())
        .productoId(oficinaProducto.getId().getProducto().getId())
        .oficinaNombre(oficinaProducto.getId().getOficina().getNombre())
        .productoNombre(oficinaProducto.getId().getProducto().getNombre())
        .stock(oficinaProducto.getStock())
        .build();
  }

  public List<OficinaProductoDTO> getAll() {
    List<OficinaProducto> oficinaProductos = oficinaProductoRepositoryPort.findAll();
    return oficinaProductos.stream().map(this::convertToDTO).collect(Collectors.toList());
  }

  public OficinaProducto saveOficinaProducto(OficinaProductoDTO oficinaProductoDTO) {
    return oficinaProductoRepositoryPort.save(OficinaProductoDTOtoEntity(oficinaProductoDTO));
  }

  public OficinaProducto getOficinaProducto(OficinaProductoDTO oficinaProductoDTO) {
    Oficina oficina = oficinaService.getById(oficinaProductoDTO.getOficinaId());
    Producto producto = productoService.getById(oficinaProductoDTO.getProductoId());
    OficinaProductoId id = new OficinaProductoId(oficina, producto);
    return oficinaProductoRepositoryPort.findById(id).orElse(null);
  }

  public OficinaProducto OficinaProductoDTOtoEntity(OficinaProductoDTO oficinaProductoDTO) {
    Oficina oficina = oficinaService.getById(oficinaProductoDTO.getOficinaId());
    Producto producto = productoService.getById(oficinaProductoDTO.getProductoId());
    OficinaProductoId id = new OficinaProductoId(oficina, producto);
    return OficinaProducto.builder()
        .id(id)
        .stock(oficinaProductoDTO.getStock())
        .build();
  }

  public OficinaProducto updateOficinaProducto(OficinaProductoDTO oficinaProductoDTO) {
    OficinaProducto existingOficinaProducto = getOficinaProducto(oficinaProductoDTO);

    existingOficinaProducto.setStock(oficinaProductoDTO.getStock());

    return oficinaProductoRepositoryPort.save(existingOficinaProducto);
  }

  public void deleteOficinaProducto(OficinaProductoDTO oficinaProductoDTO) {
    Oficina oficina = oficinaService.getById(oficinaProductoDTO.getOficinaId());
    Producto producto = productoService.getById(oficinaProductoDTO.getProductoId());
    OficinaProductoId id = new OficinaProductoId(oficina, producto);
    oficinaProductoRepositoryPort.deleteById(id);
  }
}
