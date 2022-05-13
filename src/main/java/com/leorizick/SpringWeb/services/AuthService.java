package com.leorizick.SpringWeb.services;

import com.leorizick.SpringWeb.domain.Cliente;
import com.leorizick.SpringWeb.repositories.ClienteRepository;
import com.leorizick.SpringWeb.services.exception.ObjectNotFoundException;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AuthService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private BCryptPasswordEncoder pe;

    @Autowired
    private EmailService emailService;


    @Transactional
    public void sendNewPassword(String email){

        Cliente cliente = clienteRepository.findByEmail(email);
        if(cliente == null){
            throw new ObjectNotFoundException("Email nao encontrado");
        }

        String newPass = newPassword();
        cliente.setSenha(pe.encode(newPass));

        clienteRepository.save(cliente);

        emailService.sendNewPasswordEmail(cliente, newPass);

    }

    private String newPassword() {
        return RandomStringUtils.random(10, true, true);
    }
}
