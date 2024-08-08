package com.moufflet.ecommerce_backend.proveedor.Infraestructure.adapter.in;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moufflet.ecommerce_backend.proveedor.application.ProveedorService;
import com.moufflet.ecommerce_backend.proveedor.model.ProveedorDTO;

@RequestMapping("/admin/proveedor")
@RestController
public class ProveedorController {

  @Autowired
  private ProveedorService proveedorService;

  @GetMapping("/getAll")
  public List<ProveedorDTO> getAllProveedor() {
    return proveedorService.findAll();
  }

}
