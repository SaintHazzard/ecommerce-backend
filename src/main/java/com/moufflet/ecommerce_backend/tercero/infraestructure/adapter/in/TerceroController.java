package com.moufflet.ecommerce_backend.tercero.infraestructure.adapter.in;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/tercero")
@RequiredArgsConstructor
public class TerceroController {

  @PostMapping("/register")
  public String createCliente() {
    return "Tercero created";
  }
}
