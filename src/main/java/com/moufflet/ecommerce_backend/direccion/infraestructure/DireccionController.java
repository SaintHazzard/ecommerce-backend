package com.moufflet.ecommerce_backend.direccion.infraestructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.moufflet.ecommerce_backend.direccion.application.DireccionService;

@RestController
public class DireccionController {

  @Autowired
  private DireccionService direccionService;
}
