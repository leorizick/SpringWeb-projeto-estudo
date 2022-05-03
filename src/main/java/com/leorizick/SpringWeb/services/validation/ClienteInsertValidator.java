package com.leorizick.SpringWeb.services.validation;


import com.leorizick.SpringWeb.domain.Cliente;
import com.leorizick.SpringWeb.domain.enums.TipoCliente;
import com.leorizick.SpringWeb.dto.ClienteNewDto;
import com.leorizick.SpringWeb.repositories.ClienteRepository;
import com.leorizick.SpringWeb.resources.exceptions.FieldMessage;
import com.leorizick.SpringWeb.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDto> {

    @Autowired
    private ClienteRepository repo;
    @Override
    public void initialize(ClienteInsert ann) {
    }
    @Override
    public boolean isValid(ClienteNewDto objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getDocumento())) {
            list.add(new FieldMessage("documento", "CPF invalido"));
        }
        if(objDto.getTipo().equals(TipoCliente.PESSOAJUDIRICA.getCod()) && !BR.isValidCNPJ(objDto.getDocumento())) {
            list.add(new FieldMessage("documento", "CNPJ invalido"));
        }
        Cliente aux = repo.findByEmail(objDto.getEmail());
        if(aux != null){
            list.add(new FieldMessage("email", "Email ja existente no banco de dados"));
        }
        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}