package com.moufflet.ecommerce_backend.cliente.infraestructure.adapter.in;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ClienteController {

  @PostMapping("/cliente")
  public String createCliente() {
    return "Cliente created";
  }
}
