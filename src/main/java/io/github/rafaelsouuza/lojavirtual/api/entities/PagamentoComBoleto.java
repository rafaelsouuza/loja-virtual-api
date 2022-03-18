package io.github.rafaelsouuza.lojavirtual.api.entities;

import io.github.rafaelsouuza.lojavirtual.api.entities.enums.EstadoPagamento;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class PagamentoComBoleto extends Pagamento {
    private static final long serialVersionUID = 1L;

    private LocalDate dataVencimento;
    private LocalDate dataPagemento;

    public PagamentoComBoleto() {
    }

    public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido, LocalDate dataVencimento, LocalDate dataPagemento) {
        super(id, estado, pedido);
        this.dataVencimento = dataVencimento;
        this.dataPagemento = dataPagemento;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public LocalDate getDataPagemento() {
        return dataPagemento;
    }

    public void setDataPagemento(LocalDate dataPagemento) {
        this.dataPagemento = dataPagemento;
    }


}
