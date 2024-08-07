package com.moufflet.ecommerce_backend.tercero.infraestructure.adapter.out;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moufflet.ecommerce_backend.tercero.domain.RolEnum;
import com.moufflet.ecommerce_backend.tercero.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(RolEnum name);

}
