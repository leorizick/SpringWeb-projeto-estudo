package com.leorizick.SpringWeb.services;

import com.leorizick.SpringWeb.domain.Pedido;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendOrderConfirmationEmail(Pedido obj);

    void sendEmail(SimpleMailMessage simpleMailMessage);
}
