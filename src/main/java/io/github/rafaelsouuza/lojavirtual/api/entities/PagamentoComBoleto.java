package io.github.rafaelsouuza.lojavirtual.api.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.github.rafaelsouuza.lojavirtual.api.entities.enums.EstadoPagamento;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@JsonTypeName("pagamentoComBoleto")
public class PagamentoComBoleto extends Pagamento {
    private static final long serialVersionUID = 1L;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime dataVencimento;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime dataPagemento;

    public PagamentoComBoleto() {
    }

    public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido, LocalDateTime dataVencimento, LocalDateTime dataPagemento) {
        super(id, estado, pedido);
        this.dataVencimento = dataVencimento;
        this.dataPagemento = dataPagemento;
    }

    public LocalDateTime getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDateTime dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public LocalDateTime getDataPagemento() {
        return dataPagemento;
    }

    public void setDataPagemento(LocalDateTime dataPagemento) {
        this.dataPagemento = dataPagemento;
    }


}
