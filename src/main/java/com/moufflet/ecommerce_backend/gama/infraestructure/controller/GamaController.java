package com.moufflet.ecommerce_backend.gama.infraestructure.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moufflet.ecommerce_backend.gama.application.GamaService;
import com.moufflet.ecommerce_backend.gama.model.Gama;

@RequestMapping("/public/gama")
@RestController
public class GamaController {

  @Autowired
  private GamaService gamaService;

  @GetMapping("/getAll")
  public List<Gama> getAll() {
    return gamaService.getAll();
  }

  @GetMapping("/getById")
  public Gama getById(@RequestParam Long id) {
    return gamaService.findById(id);
  }

  @PostMapping("/create")
  public Gama create(@RequestBody Gama gama) {
    return gamaService.createGama(gama);
  }

}
