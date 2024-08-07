package com.moufflet.ecommerce_backend.tercero.infraestructure.adapter.in;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moufflet.ecommerce_backend.tercero.application.TerceroService;
import com.moufflet.ecommerce_backend.tercero.domain.Tercero;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin/tercero")
@RequiredArgsConstructor
public class TerceroController {

  @Autowired
  private TerceroService terceroService;

  @PostMapping("/hola")
  public String createCliente() {
    return "Hola bro, sirve el spring security";
  }

  @GetMapping("/getAll")
  public List<Tercero> getAll() {
    return terceroService.getAll();
  }

  // @PostMapping("/newCliente")
  // public ResponseEntity<RegisterRequest> newCliente(@RequestBody
  // RegisterRequest request) {
  // terceroService.setNuevoCliente(request);
  // return ResponseEntity.ok(request);
  // }

}
