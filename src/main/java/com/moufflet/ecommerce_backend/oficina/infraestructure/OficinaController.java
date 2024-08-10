package com.moufflet.ecommerce_backend.oficina.infraestructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moufflet.ecommerce_backend.oficina.application.OficinaService;
import com.moufflet.ecommerce_backend.oficina.model.Oficina;

@RequestMapping("/admin/oficinas")
@RestController
public class OficinaController {
  @Autowired
  private OficinaService oficinaService;

  @PostMapping("/crear")
  public ResponseEntity<Oficina> crearOficina(@RequestBody Oficina oficina) {
    return ResponseEntity.ok(oficinaService.save(oficina));
  }

  @GetMapping("/buscar")
  public ResponseEntity<Oficina> buscarOficina(@RequestParam Long id) {
    return ResponseEntity.ok(oficinaService.getById(id));
  }

  @GetMapping("/borrar")
  public ResponseEntity<Void> borrarOficina(@RequestParam Long id) {
    oficinaService.deleteById(id);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/buscarPorCiudad")
  public ResponseEntity<Oficina> buscarOficinaPorCiudad(@RequestParam String ciudad) {
    return ResponseEntity.ok(oficinaService.getByDireccionCiudadNombre(ciudad));
  }
}
