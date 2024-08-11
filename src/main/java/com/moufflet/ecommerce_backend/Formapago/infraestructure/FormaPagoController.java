package com.moufflet.ecommerce_backend.Formapago.infraestructure;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moufflet.ecommerce_backend.Formapago.application.FormaPagoService;
import com.moufflet.ecommerce_backend.Formapago.model.FormaPago;
import com.moufflet.ecommerce_backend.Formapago.model.FormaPagoDTO;

@RestController
@RequestMapping("/admin/formaPago")
public class FormaPagoController {

  @Autowired
  private FormaPagoService formaPagoService;

  @PostMapping("/guardar")
  public FormaPago guardarFormaPago(@RequestBody FormaPagoDTO formaPago) {
    return formaPagoService.guardarFormaPago(formaPago);
  }

  @GetMapping("/busca/{id}")
  public FormaPago buscarFormaPagoPorId(@PathVariable Long id) {
    return formaPagoService.buscarFormaPagoPorId(id);
  }

  @DeleteMapping("/eliminar/{id}")
  public void eliminarFormaPagoPorId(@PathVariable Long id) {
    formaPagoService.eliminarFormaPagoPorId(id);
  }

  @GetMapping("/getAll")
  public ResponseEntity<List<FormaPagoDTO>> getAll() {
    return ResponseEntity.ok(formaPagoService.getAll());
  }

}
