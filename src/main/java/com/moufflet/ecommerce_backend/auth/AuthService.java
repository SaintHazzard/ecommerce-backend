package com.moufflet.ecommerce_backend.auth;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.moufflet.ecommerce_backend.auth.jwt.JwtService;
import com.moufflet.ecommerce_backend.tercero.application.port.out.TerceroRepositoryPort;
import com.moufflet.ecommerce_backend.tercero.domain.RolEnum;
import com.moufflet.ecommerce_backend.tercero.domain.Role;
import com.moufflet.ecommerce_backend.tercero.domain.Tercero;
import com.moufflet.ecommerce_backend.tercero.infraestructure.adapter.out.RoleRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

        private final TerceroRepositoryPort terceroRepositoryPort;
        private final JwtService jwtService;
        private final PasswordEncoder passwordEncoder;
        private final AuthenticationManager authenticationManager;
        private final RoleRepository roleRepository;

        public AuthResponse login(LoginRequest request) {
                authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

                UserDetails user = terceroRepositoryPort.findByUsername(request.getUsername())
                                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

                String token = jwtService.getToken(user);
                System.out.println("Generated Token: " + token);
                return AuthResponse.builder()
                                .token(token)
                                .build();
        }

        @Transactional
        public AuthResponse register(RegisterRequest request) {

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

                Tercero tercero = Tercero.builder()
                                .id(request.getId())
                                .username(request.getUsername())
                                .password(passwordEncoder.encode(password))
                                .email(request.getEmail())
                                .primerNombre(request.getPrimerNombre())
                                .primerApellido(request.getPrimerApellido())
                                .telefono(request.getTelefono())
                                .roles(roles)
                                .build();

                terceroRepositoryPort.save(tercero);

                return AuthResponse.builder().token(jwtService.getToken(tercero)).build();
        }
}
