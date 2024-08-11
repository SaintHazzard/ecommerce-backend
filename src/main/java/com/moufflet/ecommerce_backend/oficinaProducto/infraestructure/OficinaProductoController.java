package com.moufflet.ecommerce_backend.oficinaProducto.infraestructure;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moufflet.ecommerce_backend.oficinaProducto.application.OficinaProductoService;
import com.moufflet.ecommerce_backend.oficinaProducto.model.OficinaProducto;
import com.moufflet.ecommerce_backend.oficinaProducto.model.OficinaProductoDTO;
import com.moufflet.ecommerce_backend.oficinaProducto.model.OficinaProductoId;

@RestController
@RequestMapping("/admin/oficinaProducto")
public class OficinaProductoController {

  @Autowired
  private OficinaProductoService oficinaProductoService;

  @GetMapping("/getAll")
  public List<OficinaProductoDTO> getAll() {
    return oficinaProductoService.getAll();
  }

  @PostMapping("/save")
  public OficinaProducto saveOficinaProducto(OficinaProductoDTO oficinaProductoDTO) {
    return oficinaProductoService.saveOficinaProducto(oficinaProductoDTO);
  }

  @GetMapping("/getById")
  public OficinaProducto getOficinaProducto(OficinaProductoDTO oficinaProductoDTO) {
    return oficinaProductoService.getOficinaProducto(oficinaProductoDTO);
  }

  @DeleteMapping("/delete")
  public void deleteOficinaProducto(OficinaProductoDTO oficinaProductoDTO) {
    oficinaProductoService.deleteOficinaProducto(oficinaProductoDTO);
  }

  @PutMapping("/update")
  public OficinaProducto updateOficinaProducto(OficinaProductoDTO oficinaProductoDTO) {
    return oficinaProductoService.updateOficinaProducto(oficinaProductoDTO);
  }

}
