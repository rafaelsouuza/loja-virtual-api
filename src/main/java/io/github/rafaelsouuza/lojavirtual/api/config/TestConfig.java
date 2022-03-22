package io.github.rafaelsouuza.lojavirtual.api.config;

import io.github.rafaelsouuza.lojavirtual.api.services.DBService;
import io.github.rafaelsouuza.lojavirtual.api.services.EmailService;
import io.github.rafaelsouuza.lojavirtual.api.services.MockEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBService dbService;

    @Bean
    public void instanciaDB() {
        this.dbService.instanciaDB();
    }

    @Bean
    public EmailService emailService() {
        return new MockEmailService();
    }
}
