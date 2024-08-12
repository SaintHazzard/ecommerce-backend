package com.moufflet.ecommerce_backend.formaPagoTercero.infraestructure;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moufflet.ecommerce_backend.formaPagoTercero.application.FormaPagoTerceroService;
import com.moufflet.ecommerce_backend.formaPagoTercero.model.FormaPagoTerceroDTO;

@RestController
@RequestMapping("/admin/formaPagoTercero")
public class FormaPagoTerceroController {

  @Autowired
  private FormaPagoTerceroService formaPagoTerceroService;

  @GetMapping("/getAll")
  public ResponseEntity<List<FormaPagoTerceroDTO>> buscarFormaPagoPorTerceroId() {
    return ResponseEntity.ok(formaPagoTerceroService.getAll());
  }

  @GetMapping("/getByFormaPagoId")
  public ResponseEntity<List<FormaPagoTerceroDTO>> buscarPagoPorFormaPagoId(@RequestParam Long formaPagoId) {
    return ResponseEntity.ok(formaPagoTerceroService.buscarFormaPagoTerceroPorFormaPagoId(formaPagoId));
  }

  @GetMapping("/getByTerceroId")
  public ResponseEntity<List<FormaPagoTerceroDTO>> buscarPagoPorTercero(@RequestParam Long pedidoId,
      @RequestParam String terceroId) {
    return ResponseEntity.ok(formaPagoTerceroService.buscarFormaPagoTerceroPorTerceroId(terceroId));
  }

  @PostMapping("/create")
  public ResponseEntity<FormaPagoTerceroDTO> guardarFormaPagoTercero(
      @RequestBody FormaPagoTerceroDTO formaPagoTerceroDTO) {
    return ResponseEntity.ok(formaPagoTerceroService.guardarFormaPagoTercero(formaPagoTerceroDTO));
  }

}
