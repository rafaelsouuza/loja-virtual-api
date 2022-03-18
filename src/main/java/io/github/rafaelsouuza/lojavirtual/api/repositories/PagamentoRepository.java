package io.github.rafaelsouuza.lojavirtual.api.repositories;

import io.github.rafaelsouuza.lojavirtual.api.entities.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {
}
