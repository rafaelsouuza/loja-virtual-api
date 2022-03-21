package io.github.rafaelsouuza.lojavirtual.api.repositories;

import io.github.rafaelsouuza.lojavirtual.api.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @Transactional(readOnly = true)
    Cliente findByEmail(String email);
}
