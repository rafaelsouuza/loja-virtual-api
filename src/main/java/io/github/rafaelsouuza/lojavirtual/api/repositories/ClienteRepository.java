package io.github.rafaelsouuza.lojavirtual.api.repositories;

import io.github.rafaelsouuza.lojavirtual.api.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
