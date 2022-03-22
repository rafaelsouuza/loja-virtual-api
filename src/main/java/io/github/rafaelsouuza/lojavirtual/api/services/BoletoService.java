package io.github.rafaelsouuza.lojavirtual.api.services;

import io.github.rafaelsouuza.lojavirtual.api.entities.PagamentoComBoleto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BoletoService {

    public void prencherPagamentoComBoleto(PagamentoComBoleto pagto, LocalDateTime instanteDoPedido) {
        LocalDateTime cal = instanteDoPedido;
        cal.plusDays(7);
        pagto.setDataVencimento(cal);
    }
}
