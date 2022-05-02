package com.leorizick.SpringWeb.services.validation;


import com.leorizick.SpringWeb.domain.enums.TipoCliente;
import com.leorizick.SpringWeb.dto.ClienteNewDto;
import com.leorizick.SpringWeb.resources.exceptions.FieldMessage;
import com.leorizick.SpringWeb.services.validation.utils.BR;


import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDto> {
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
        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}