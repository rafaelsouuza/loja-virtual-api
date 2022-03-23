package io.github.rafaelsouuza.lojavirtual.api.services;

import io.github.rafaelsouuza.lojavirtual.api.dtos.ClienteDTO;
import io.github.rafaelsouuza.lojavirtual.api.dtos.ClienteNewDTO;
import io.github.rafaelsouuza.lojavirtual.api.entities.Cidade;
import io.github.rafaelsouuza.lojavirtual.api.entities.Cliente;
import io.github.rafaelsouuza.lojavirtual.api.entities.Endereco;
import io.github.rafaelsouuza.lojavirtual.api.entities.enums.TipoCliente;
import io.github.rafaelsouuza.lojavirtual.api.repositories.ClienteRepository;
import io.github.rafaelsouuza.lojavirtual.api.repositories.EnderecoRepository;
import io.github.rafaelsouuza.lojavirtual.api.services.exceptions.DataIntegrityException;
import io.github.rafaelsouuza.lojavirtual.api.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


//    public ClienteDTO findById(Integer id) {
//        Optional<Cliente> obj = clienteRepository.findById(id);
//        obj.orElseThrow(() -> new ResourceNotFoundException("Recurso não Encontrado: Id: " + id));
//        return new ClienteDTO(obj.get());
//    }

    public Cliente findById(Integer id) {
        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException("Recurso não Encontrado: Id: " + id));
    }

    @Transactional
    public Cliente insert(Cliente obj) {
        obj.setId(null);
        obj = clienteRepository.save(obj);
        enderecoRepository.saveAll(obj.getEnderecos());
        return obj;
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
            throw new DataIntegrityException("Não é possivel exluir porque há pedidos relacionados");
        }
    }

    public Page<ClienteDTO> findAll(Pageable pageable) {
        Page<Cliente> list = clienteRepository.findAll(pageable);
        return list.map(x -> new ClienteDTO(x));
    }

    public Cliente fromDTO(ClienteNewDTO objDto) {
        Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(),
                TipoCliente.toEnum(objDto.getTipo()), bCryptPasswordEncoder.encode(objDto.getSenha()));

        Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
        Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(),
                objDto.getBairro(), objDto.getCep(), cli, cid);

        cli.getEnderecos().add(end);
        cli.getTelefones().add(objDto.getTelefone1());

        if (objDto.getTelefone2() != null) {
            cli.getTelefones().add(objDto.getTelefone2());
        }
        if (objDto.getTelefone3() != null) {
            cli.getTelefones().add(objDto.getTelefone3());
        }
        return cli;
    }

    private static void copyDtoToEntity(ClienteDTO dto, Cliente entity) {
        entity.setNome(dto.getNome());
        entity.setEmail(dto.getEmail());
    }
}

