/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.moufflet.ecommerce_backend.gama.application.port;

import java.util.List;
import java.util.Optional;

import com.moufflet.ecommerce_backend.gama.model.Gama;

public interface GamaRepositoryPort {

  Gama save(Gama gama);

  Optional<Gama> findById(Long id);

  void deleteById(Long id);

  List<Gama> findAll();

}
