package com.moufflet.ecommerce_backend.direccion.infraestructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moufflet.ecommerce_backend.direccion.application.DireccionService;
import com.moufflet.ecommerce_backend.direccion.model.Direccion;

@RestController
@RequestMapping("/admin/direccion")
public class DireccionController {

  @Autowired
  private DireccionService direccionService;

  @PostMapping("/crear")
  public Direccion crearDireccion(Direccion direccion) {
    return direccionService.save(direccion);
  }

  @PostMapping("/buscar")
  public Direccion buscarDireccion(Long id) {
    return direccionService.getById(id);
  }
}
