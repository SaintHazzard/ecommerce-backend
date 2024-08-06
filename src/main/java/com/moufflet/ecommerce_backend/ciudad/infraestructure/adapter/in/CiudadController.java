package com.moufflet.ecommerce_backend.ciudad.infraestructure.adapter.in;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moufflet.ecommerce_backend.ciudad.application.CiudadService;
import com.moufflet.ecommerce_backend.ciudad.model.Ciudad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/public/ciudad")
public class CiudadController {

  @Autowired
  private CiudadService ciudadService;

  @GetMapping("/getAll")
  public List<Ciudad> getAll() {
    return ciudadService.getAll();
  }

  @GetMapping("/getByNombre")
  public Ciudad getByName(@RequestParam String nombre) {
    return ciudadService.getPorNombre(nombre);
  }

}
