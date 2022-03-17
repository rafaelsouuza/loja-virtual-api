package io.github.rafaelsouuza.lojavirtual.api.config;

import io.github.rafaelsouuza.lojavirtual.api.entities.Categoria;
import io.github.rafaelsouuza.lojavirtual.api.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public void run(String... args) throws Exception {

        Categoria c1 = new Categoria(null, "Infomática");
        Categoria c2 = new Categoria(null, "Escritório");

        categoriaRepository.saveAll(Arrays.asList(c1, c2));

    }
}
