package io.github.rafaelsouuza.lojavirtual.api.services;

import io.github.rafaelsouuza.lojavirtual.api.entities.Pedido;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

public interface EmailService {

    void sendOrderConfirmationEmail(Pedido obj);

    void sendEmail(SimpleMailMessage msg);

    void sendOrderConfirmationHtmlEmail(Pedido obj);

    void sendHtmlEmail(MimeMessage msg);
}
