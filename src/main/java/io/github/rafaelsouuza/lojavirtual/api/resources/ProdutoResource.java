package io.github.rafaelsouuza.lojavirtual.api.resources;

import io.github.rafaelsouuza.lojavirtual.api.dtos.ProdutoDTO;
import io.github.rafaelsouuza.lojavirtual.api.entities.Produto;
import io.github.rafaelsouuza.lojavirtual.api.resources.utils.URL;
import io.github.rafaelsouuza.lojavirtual.api.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoResource {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/{id}")
    public ResponseEntity<Produto> findById(@PathVariable Integer id) {
        Produto obj = produtoService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public ResponseEntity<Page<ProdutoDTO>> findAll(@RequestParam(value = "nome", defaultValue = "") String nome,
                                                    @RequestParam(value = "categorias", defaultValue = "") String categorias,
                                                    Pageable pageable) {
        String nomeDecoded = URL.decodeParam(nome);
        List<Integer> ids = URL.decodeIntList(categorias);
        Page<Produto> list = produtoService.search(nomeDecoded, ids, pageable);
        Page<ProdutoDTO> listDto = list.map(obj -> new ProdutoDTO(obj));
        return ResponseEntity.ok().body(listDto);
    }
}
