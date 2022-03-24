package io.github.rafaelsouuza.lojavirtual.api.resources;

import io.github.rafaelsouuza.lojavirtual.api.dtos.CategoriaDTO;
import io.github.rafaelsouuza.lojavirtual.api.entities.Categoria;
import io.github.rafaelsouuza.lojavirtual.api.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> findById(@PathVariable Integer id) {
        CategoriaDTO dto = categoriaService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<CategoriaDTO> insert(@Valid @RequestBody CategoriaDTO dto) {
        CategoriaDTO obj = categoriaService.insert(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> update(@RequestBody CategoriaDTO dto, @PathVariable Integer id) {
        CategoriaDTO obj = categoriaService.update(dto, id);
        return ResponseEntity.ok().body(obj);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        categoriaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<CategoriaDTO>> findAll(Pageable pageable) {
        Page<CategoriaDTO> list = categoriaService.findAll(pageable);
        return ResponseEntity.ok().body(list);
    }
}













