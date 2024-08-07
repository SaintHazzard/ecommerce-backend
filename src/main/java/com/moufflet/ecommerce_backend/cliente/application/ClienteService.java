package com.moufflet.ecommerce_backend.cliente.application;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.moufflet.ecommerce_backend.auth.AuthResponse;
import com.moufflet.ecommerce_backend.auth.RegisterRequest;
import com.moufflet.ecommerce_backend.cliente.domain.ClientDTOdash;
import com.moufflet.ecommerce_backend.cliente.domain.Cliente;
import com.moufflet.ecommerce_backend.tercero.domain.RolEnum;
import com.moufflet.ecommerce_backend.tercero.domain.Role;
import com.moufflet.ecommerce_backend.tercero.domain.Tercero;
import com.moufflet.ecommerce_backend.tercero.infraestructure.adapter.out.RoleRepository;

import jakarta.transaction.Transactional;

@Service
public class ClienteService {

  @Autowired
  private ClienteRepositoryPort clienteRepositoryPort;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public List<ClientDTOdash> findAll() {
    List<Cliente> clientes = clienteRepositoryPort.findAll();
    return clientes.stream().map(cli -> getClienteDashboard(cli)).toList();
  }

  public Cliente save(Cliente cliente) {
    return clienteRepositoryPort.save(cliente);
  }

  public Cliente findById(String id) {
    return clienteRepositoryPort.findById(id).orElse(null);
  }

  public void deleteById(String id) {
    clienteRepositoryPort.deleteById(id);
  }

  public Cliente findByEmail(String email) {
    return clienteRepositoryPort.findByEmail(email).orElse(null);
  }

  public Cliente findByTelefono(String telefono) {
    return clienteRepositoryPort.findByTelefono(telefono).orElse(null);
  }

  public ClientDTOdash getClienteDashboard(Cliente cliente) {
    return ClientDTOdash.builder()
        .id(cliente.getId())
        .nombre(cliente.getPrimerNombre())
        .apellido(cliente.getPrimerApellido())
        .email(cliente.getEmail())
        .telefono(cliente.getTelefono())
        .credito(cliente.getCredito())
        .build();
  }

  public List<ClientDTOdash> findByDireccionCiudadNombre(String nombre) {
    List<Cliente> clientes = clienteRepositoryPort.findByDireccionCiudadNombre(nombre);
    return clientes.stream().map(cli -> getClienteDashboard(cli)).toList();
  }

  @Transactional
  public Cliente register(RegisterRequest request) {

    String password = request.getPassword() != null ? request.getPassword() : "0000";
    List<Role> roles = new ArrayList<>();

    if (request.getRole() == null || request.getRole().isEmpty()) {
      Role defaultRole = roleRepository.findByName(RolEnum.USER)
          .orElseThrow(() -> new RuntimeException("Default role USER not found"));
      roles.add(defaultRole);
    } else {
      roles = request.getRole().stream()
          .map(roleId -> roleRepository.findById(roleId)
              .orElseThrow(() -> new RuntimeException("Role not found")))
          .collect(Collectors.toList());
    }

    Cliente cliente = new Cliente();
    cliente.setId(request.getId());
    cliente.setUsername(request.getUsername());
    cliente.setPassword(passwordEncoder.encode(password));
    cliente.setEmail(request.getEmail());
    cliente.setPrimerNombre(request.getPrimerNombre());
    cliente.setPrimerApellido(request.getPrimerApellido());
    cliente.setTelefono(request.getTelefono());
    cliente.setRoles(roles);
    cliente.setCredito(BigDecimal.valueOf(1000000));

    return clienteRepositoryPort.save(cliente);
  }
}