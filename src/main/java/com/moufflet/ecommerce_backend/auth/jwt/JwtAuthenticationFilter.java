package com.moufflet.ecommerce_backend.auth.jwt;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  // @Autowired
  // private JwtTokenProvider jwtTokenProvider;

  // @Autowired
  // private CustomUserDetailsService customUserDetailsService;

  @SuppressWarnings("null")
  @Override
  protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    final String token = getTokenFromRequest(request);
    if (token == null) {
      filterChain.doFilter(request, response);
      return;
    }
    filterChain.doFilter(request, response);
  }

  private String getTokenFromRequest(HttpServletRequest request) {
    final String token = request.getHeader(HttpHeaders.AUTHORIZATION);
    if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
      return token.substring(7);
    } else {
      return null;
    }
  }

  protected void doFilterInternal1(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    // final String token = getTokenFromRequest(request);
    // if (token != null && jwtTokenProvider.validateToken(token)) {
    // final String username = jwtTokenProvider.getUsernameFromToken(token);
    // final UserDetails userDetails =
    // customUserDetailsService.loadUserByUsername(username);
    // final UsernamePasswordAuthenticationToken authentication = new
    // UsernamePasswordAuthenticationToken(
    // userDetails, null, userDetails.getAuthorities());
    // SecurityContextHolder.getContext().setAuthentication(authentication);
    // }
    throw new UnsupportedOperationException("Unimplemented method 'doFilterInternal'");
  }

}