package com.moufflet.ecommerce_backend.tercero.infraestructure.adapter.in;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moufflet.ecommerce_backend.tercero.application.TerceroService;
import com.moufflet.ecommerce_backend.tercero.domain.Tercero;
import com.moufflet.ecommerce_backend.tercero.domain.TerceroDTO;

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

  @GetMapping("/pedidosPendientes")
  public ResponseEntity<List<TerceroDTO>> getPedidosPendientes() {
    List<TerceroDTO> pendientes = terceroService.buscarClientesConPedidosPendientes("PENDIENTE");
    return ResponseEntity.ok(pendientes);
  }

}
