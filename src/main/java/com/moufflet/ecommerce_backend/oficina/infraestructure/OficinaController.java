package com.moufflet.ecommerce_backend.oficina.infraestructure;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moufflet.ecommerce_backend.oficina.application.OficinaService;
import com.moufflet.ecommerce_backend.oficina.model.Oficina;
import com.moufflet.ecommerce_backend.oficina.model.OficinaDTO;

@RequestMapping("/admin/oficinas")
@RestController
public class OficinaController {
  @Autowired
  private OficinaService oficinaService;

  @PostMapping("/create")
  public ResponseEntity<Oficina> crearOficina(@RequestBody OficinaDTO oficina) {
    return ResponseEntity.ok(oficinaService.save(oficina));
  }

  @GetMapping("/getAll")
  public ResponseEntity<List<OficinaDTO>> getAllOficinas() {
    return ResponseEntity.ok(oficinaService.getAllOficinas());
  }

  @GetMapping("/getById")
  public ResponseEntity<Oficina> buscarOficina(@RequestParam Long id) {
    return ResponseEntity.ok(oficinaService.getById(id));
  }

  @DeleteMapping("/delete")
  public ResponseEntity<Void> borrarOficina(@RequestParam Long id) {
    oficinaService.deleteById(id);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/getByCiudad")
  public ResponseEntity<Oficina> buscarOficinaPorCiudad(@RequestParam String ciudad) {
    return ResponseEntity.ok(oficinaService.getByDireccionCiudadNombre(ciudad));
  }

  @PutMapping("/update")
  public ResponseEntity<Oficina> actualizarOficina(@RequestBody OficinaDTO oficina) {
    return ResponseEntity.ok(oficinaService.update(oficina));
  }

}
