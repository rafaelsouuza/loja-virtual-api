package io.github.rafaelsouuza.lojavirtual.api.repositories;

import io.github.rafaelsouuza.lojavirtual.api.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
}
