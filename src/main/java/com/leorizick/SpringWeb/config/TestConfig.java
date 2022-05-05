package com.leorizick.SpringWeb.config;

import com.leorizick.SpringWeb.services.DBService;
import com.leorizick.SpringWeb.services.EmailService;
import com.leorizick.SpringWeb.services.MockEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.validation.constraints.Email;
import java.text.ParseException;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBService dbService;

    @Bean
    public boolean instantiateDataBase() throws ParseException {
        dbService.instantiateTestDatabase();

        return  true;
    }

    @Bean
    public EmailService emailService(){
        return new MockEmailService();
    }
}
