package com.moufflet.ecommerce_backend.direccion.infraestructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moufflet.ecommerce_backend.direccion.application.DireccionService;
import com.moufflet.ecommerce_backend.direccion.model.Direccion;
import com.moufflet.ecommerce_backend.direccion.model.DireccionDTO;

@RestController
@RequestMapping("/admin/direccion")
public class DireccionController {

  @Autowired
  private DireccionService direccionService;

  @PostMapping("/create")
  public Direccion crearDireccion(DireccionDTO direccion) {
    return direccionService.save(direccion);
  }

  @GetMapping("/getById")
  public Direccion buscarDireccion(Long id) {
    return direccionService.getById(id);
  }

  @DeleteMapping("/delete")
  public void borrarDireccion(Long id) {
    direccionService.deleteById(id);
  }

}
