package io.github.rafaelsouuza.lojavirtual.api.services;

import io.github.rafaelsouuza.lojavirtual.api.entities.Categoria;
import io.github.rafaelsouuza.lojavirtual.api.entities.Produto;
import io.github.rafaelsouuza.lojavirtual.api.repositories.CategoriaRepository;
import io.github.rafaelsouuza.lojavirtual.api.repositories.ProdutoRepository;
import io.github.rafaelsouuza.lojavirtual.api.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Produto findById(Integer id) {
        Optional<Produto> obj = produtoRepository.findById(id);
        obj.orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado: Id:" + id));
        return obj.get();
    }

    public Page<Produto> search(String nome, List<Integer> ids, Pageable pageable) {
        List<Categoria> categorias = categoriaRepository.findAllById(ids);
        return produtoRepository.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageable);
    }
}
