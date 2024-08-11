package com.moufflet.ecommerce_backend.tercero.application;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moufflet.ecommerce_backend.pedido.model.EstadoPedido;
import com.moufflet.ecommerce_backend.tercero.application.port.out.TerceroRepositoryPort;
import com.moufflet.ecommerce_backend.tercero.domain.Tercero;
import com.moufflet.ecommerce_backend.tercero.domain.TerceroDTO;

@Service
public class TerceroService {

  @Autowired
  private TerceroRepositoryPort terceroRepositoryPort;

  public Tercero save(Tercero tercero) {
    return terceroRepositoryPort.save(tercero);
  }

  public void deleteById(String id) {
    terceroRepositoryPort.deleteById(id);
  }

  public boolean existsById(String id) {
    return terceroRepositoryPort.existsById(id);
  }

  public void deleteByUsername(String username) {
    terceroRepositoryPort.findByUsername(username).ifPresent(terceroRepositoryPort::delete);
  }

  public void deleteByEmail(String email) {
    terceroRepositoryPort.findByEmail(email).ifPresent(terceroRepositoryPort::delete);
  }

  public Tercero getById(String id) {
    return terceroRepositoryPort.findById(id).orElse(null);
  }

  public Tercero getByUsername(String username) {
    return terceroRepositoryPort.findByUsername(username).orElse(null);
  }

  public List<Tercero> getAll() {
    return terceroRepositoryPort.findAll();
  }

  public TerceroDTO entityToDTO(Tercero tercero) {
    return TerceroDTO.builder()
        .id(tercero.getId())
        .email(tercero.getEmail())
        .nombre(tercero.getPrimerNombre())
        .apellido(tercero.getPrimerApellido())
        .telefono(tercero.getTelefono())
        .build();
  }

  public List<TerceroDTO> buscarClientesConPedidosPendientes(String estado) {
    EstadoPedido estadoPedido = EstadoPedido.valueOf(estado);
    List<Tercero> clientesPendientes = terceroRepositoryPort.findByformaPagoTercerosPedidosEstado(estadoPedido);
    return clientesPendientes.stream().map(this::entityToDTO).collect(Collectors.toList());
  }

}
