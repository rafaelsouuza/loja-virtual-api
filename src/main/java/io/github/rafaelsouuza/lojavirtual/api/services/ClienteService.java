package io.github.rafaelsouuza.lojavirtual.api.services;

import io.github.rafaelsouuza.lojavirtual.api.dtos.ClienteDTO;
import io.github.rafaelsouuza.lojavirtual.api.entities.Cliente;
import io.github.rafaelsouuza.lojavirtual.api.repositories.ClienteRepository;
import io.github.rafaelsouuza.lojavirtual.api.services.exceptions.DataIntegrityException;
import io.github.rafaelsouuza.lojavirtual.api.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

//    public ClienteDTO findById(Integer id) {
//        Optional<Cliente> obj = clienteRepository.findById(id);
//        obj.orElseThrow(() -> new ResourceNotFoundException("Recurso não Encontrado: Id: " + id));
//        return new ClienteDTO(obj.get());
//    }

    public Cliente findById(Integer id) {
        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException("Recurso não Encontrado: Id: " + id));
    }

    public ClienteDTO insert(ClienteDTO dto) {
        Cliente entity = new Cliente();
        copyDtoToEntity(dto, entity);
        entity = clienteRepository.save(entity);
        return new ClienteDTO(entity);
    }

    public ClienteDTO update(Integer id, ClienteDTO dto) {
        try {
            Cliente entity = clienteRepository.getById(id);
            copyDtoToEntity(dto, entity);
            entity = clienteRepository.save(entity);
            return new ClienteDTO(entity);

        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não Encontrado: Id: " + id);
        }
    }

    public void delete(Integer id) {
        try {
            Cliente obj = clienteRepository.getById(id);
            clienteRepository.deleteById(id);

        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Recurso não Encontrado: Id: " + id);

        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Violação de integridade do banco de dados");
        }
    }

    public Page<ClienteDTO> findAll(Pageable pageable) {
        Page<Cliente> list = clienteRepository.findAll(pageable);
        return list.map(x -> new ClienteDTO(x));
    }

    private static void copyDtoToEntity(ClienteDTO dto, Cliente entity) {
        entity.setNome(dto.getNome());
        entity.setEmail(dto.getEmail());
    }
}

