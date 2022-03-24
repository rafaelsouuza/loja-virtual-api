package io.github.rafaelsouuza.lojavirtual.api.repositories;

import io.github.rafaelsouuza.lojavirtual.api.entities.Cliente;
import io.github.rafaelsouuza.lojavirtual.api.entities.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    @Transactional(readOnly = true)
    Page<Pedido> findByCliente(Cliente cliente, Pageable pageable);
}
