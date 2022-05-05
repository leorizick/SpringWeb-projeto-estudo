package com.leorizick.SpringWeb.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leorizick.SpringWeb.domain.PagamentoComBoleto;
import com.leorizick.SpringWeb.domain.PagamentoComCartao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfig {
    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
            public void configure(ObjectMapper objectMapper) {
                super.configure(objectMapper);
            };
        };
        return builder;
    }
}

