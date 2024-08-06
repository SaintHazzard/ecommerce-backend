package com.moufflet.ecommerce_backend.cliente.infraestructure.adapter.in;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moufflet.ecommerce_backend.cliente.application.ClienteService;
import com.moufflet.ecommerce_backend.cliente.domain.Cliente;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("admin/cliente")
@RequiredArgsConstructor
public class ClienteController {

  private final ClienteService clienteService;

  @GetMapping("/getAll")
  public List<Cliente> getAllCliente() {
    return clienteService.findAll();
  }

  @GetMapping("/getById")
  public Cliente getById(@RequestParam String id) {
    return clienteService.findById(id);
  }

}
