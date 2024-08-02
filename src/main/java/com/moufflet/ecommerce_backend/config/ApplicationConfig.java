package com.moufflet.ecommerce_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.moufflet.ecommerce_backend.tercero.application.port.out.TerceroRepositoryPort;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

  private TerceroRepositoryPort terceroRepositoryPort;

  // @Bean
  // public AuthenticationManager
  // authenticationManager(AuthenticationConfiguration authConfig) throws
  // Exception {
  // return authConfig.getAuthenticationManager();
  // }

  // @Bean
  // public DaoAuthenticationProvider authenticationProvider() {
  // DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
  // authProvider.setUserDetailsService(userDetailsService());
  // authProvider.setPasswordEncoder(passwordEncoder());
  // return authProvider;
  // }

  // @Bean
  // public PasswordEncoder passwordEncoder() {
  // return new BCryptPasswordEncoder();
  // }

  @Bean
  public UserDetailsService userDetailsService() {
    return username -> terceroRepositoryPort.findByUsername(username)
        .orElseThrow(() -> new RuntimeException("User not found"));
  }
}
