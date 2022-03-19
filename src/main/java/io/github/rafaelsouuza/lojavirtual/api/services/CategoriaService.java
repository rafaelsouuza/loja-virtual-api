package io.github.rafaelsouuza.lojavirtual.api.services;

import io.github.rafaelsouuza.lojavirtual.api.entities.Categoria;
import io.github.rafaelsouuza.lojavirtual.api.repositories.CategoriaRepository;
import io.github.rafaelsouuza.lojavirtual.api.services.exceptions.DataIntegrityException;
import io.github.rafaelsouuza.lojavirtual.api.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria findById(Integer id) {
        Optional<Categoria> obj = categoriaRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado: Id:" + id));
    }

    public Categoria insert(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria update(Categoria categoria, Integer id) {
        Categoria obj = findById(id);
        obj.setNome(categoria.getNome());
        return obj;
    }

    public void deleteById(Integer id) {
        try {
            categoriaRepository.deleteById(id);

        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Recurso não encontrado: Id:" + id);

        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Violação de integridade do banco de dados");
        }
    }
}
