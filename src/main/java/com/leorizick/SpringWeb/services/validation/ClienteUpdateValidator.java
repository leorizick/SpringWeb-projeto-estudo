package com.leorizick.SpringWeb.services.validation;


import com.leorizick.SpringWeb.domain.Cliente;
import com.leorizick.SpringWeb.domain.enums.TipoCliente;
import com.leorizick.SpringWeb.dto.ClienteDTO;
import com.leorizick.SpringWeb.dto.ClienteNewDto;
import com.leorizick.SpringWeb.repositories.ClienteRepository;
import com.leorizick.SpringWeb.resources.exceptions.FieldMessage;
import com.leorizick.SpringWeb.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ClienteRepository repo;
    @Override
    public void initialize(ClienteUpdate ann) {
    }
    @Override
    public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {

        Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer UriId = Integer.parseInt(map.get("id"));

        List<FieldMessage> list = new ArrayList<>();

        Cliente aux = repo.findByEmail(objDto.getEmail());
        if(aux != null && !aux.getId().equals(UriId)){
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