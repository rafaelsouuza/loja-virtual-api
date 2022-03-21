package io.github.rafaelsouuza.lojavirtual.api.services.validation;

import io.github.rafaelsouuza.lojavirtual.api.dtos.ClienteNewDTO;
import io.github.rafaelsouuza.lojavirtual.api.entities.enums.TipoCliente;
import io.github.rafaelsouuza.lojavirtual.api.resources.exceptions.FieldMessage;
import io.github.rafaelsouuza.lojavirtual.api.services.validation.utils.BR;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

    @Override
    public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        if (objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
            list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
        }

        if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
            list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }

        return list.isEmpty();
    }

    @Override
    public void initialize(ClienteInsert constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
