package com.moufflet.ecommerce_backend.tercero.domain;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
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
  @Column(unique = true, nullable = false)
  private String username;
  @Column(nullable = false)
  private String password;
  @Column(unique = true, nullable = false)
  private String email;
  @Column(nullable = false)
  private String primerNombre;
  private String segundoNombre;
  @Column(nullable = false)
  private String primerApellido;
  private String segundoApellido;
  @Column(nullable = false)
  private String telefono;
  @Enumerated(EnumType.STRING)
  RolTercero rol;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority((rol.name())));
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
}
