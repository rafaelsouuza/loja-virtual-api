package io.github.rafaelsouuza.lojavirtual.api.services;

import io.github.rafaelsouuza.lojavirtual.api.dtos.CategoriaDTO;
import io.github.rafaelsouuza.lojavirtual.api.entities.Categoria;
import io.github.rafaelsouuza.lojavirtual.api.repositories.CategoriaRepository;
import io.github.rafaelsouuza.lojavirtual.api.services.exceptions.DataIntegrityException;
import io.github.rafaelsouuza.lojavirtual.api.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public CategoriaDTO findById(Integer id) {
        Optional<Categoria> obj = categoriaRepository.findById(id);
        obj.orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado: Id:" + id));
        return new CategoriaDTO(obj.get());
    }

    public CategoriaDTO insert(CategoriaDTO dto) {
        Categoria obj = new Categoria();
        copyDtoToEntity(dto, obj);
        categoriaRepository.save(obj);
        return new CategoriaDTO(obj);
    }

    public CategoriaDTO update(CategoriaDTO dto, Integer id) {
        try {
            Categoria obj = categoriaRepository.findById(id).get();
            copyDtoToEntity(dto, obj);
            categoriaRepository.save(obj);

            return new CategoriaDTO(obj);

        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Recurso não encontrado: Id:" + id);
        }
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

    public Page<CategoriaDTO> findAll(Pageable pageable) {
        Page<Categoria> list = categoriaRepository.findAll(pageable);
        return list.map(x -> new CategoriaDTO(x));
    }

    private static void copyDtoToEntity(CategoriaDTO dto, Categoria entity) {
        entity.setNome(dto.getNome());
    }
}
