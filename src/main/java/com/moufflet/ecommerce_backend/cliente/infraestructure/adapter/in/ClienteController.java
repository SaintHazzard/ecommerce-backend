package com.moufflet.ecommerce_backend.cliente.infraestructure.adapter.in;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moufflet.ecommerce_backend.auth.RegisterRequest;
import com.moufflet.ecommerce_backend.cliente.application.ClienteService;
import com.moufflet.ecommerce_backend.cliente.domain.ClientDTOdash;
import com.moufflet.ecommerce_backend.cliente.domain.Cliente;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin/cliente")
@RequiredArgsConstructor
public class ClienteController {

  private final ClienteService clienteService;

  @GetMapping("/getAll")
  public List<ClientDTOdash> getAllCliente() {
    return clienteService.findAll();
  }

  @GetMapping("/getById")
  public Cliente getById(@RequestParam String id) {
    return clienteService.findById(id);
  }

  @GetMapping("/getAllClientesByCity")
  public List<ClientDTOdash> getAllClientesByCity(@RequestParam String city) {
    return clienteService.findByDireccionCiudadNombre(city);
  }

  @PostMapping("/newCliente")
  public ResponseEntity<Cliente> createNewCliente(@RequestBody RegisterRequest request) {
    Cliente cliente = clienteService.register(request);
    return new ResponseEntity<>(cliente, HttpStatus.CREATED);
  }

  @DeleteMapping("/deleteCliente")
  public ResponseEntity<String> deleteCliente(@RequestParam String id) {
    clienteService.deleteById(id);
    return new ResponseEntity<>("Cliente eliminado", HttpStatus.OK);
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<Cliente> updateCliente(@PathVariable String id, @RequestBody Cliente cliente) {
    Cliente updatedCliente = clienteService.save(id, cliente);
    return new ResponseEntity<>(updatedCliente, HttpStatus.OK);
  }
}
