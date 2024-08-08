package com.moufflet.ecommerce_backend.pedido.infraestructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moufflet.ecommerce_backend.pedido.application.PedidoRepositoryPort;
import com.moufflet.ecommerce_backend.pedido.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>, PedidoRepositoryPort {

}
