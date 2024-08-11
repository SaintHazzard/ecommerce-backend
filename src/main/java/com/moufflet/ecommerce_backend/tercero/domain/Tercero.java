package com.moufflet.ecommerce_backend.tercero.domain;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.moufflet.ecommerce_backend.formaPagoTercero.model.FormaPagoTercero;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tercero implements UserDetails {

  @Id
  @Column(nullable = false, unique = true)
  private String id;
  // @Column(unique = true, nullable = true)
  private String username;
  // @Column(nullable = false)
  private String password;
  // @Column(unique = true, nullable = false)
  private String email;
  @Column(nullable = false)
  private String primerNombre;
  private String segundoNombre;
  @Column(nullable = false)
  private String primerApellido;
  private String segundoApellido;
  // @Column(nullable = false)
  private String telefono;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "tercero_rol", joinColumns = @JoinColumn(name = "tercero_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"))
  private List<Role> roles;

  @OneToMany(mappedBy = "tercero")
  private List<FormaPagoTercero> formaPagoTerceros;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    if (roles == null) {
      System.out.println("Roles es null");
      return Collections.emptyList();
    }
    return roles.stream()
        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName().name()))
        .collect(Collectors.toList());
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  public Tercero(String id) {
    this.id = id;
  }
}
