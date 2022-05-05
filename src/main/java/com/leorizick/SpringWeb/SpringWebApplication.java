package com.leorizick.SpringWeb;

import com.leorizick.SpringWeb.domain.*;
import com.leorizick.SpringWeb.domain.enums.EstadoPagamento;
import com.leorizick.SpringWeb.domain.enums.TipoCliente;
import com.leorizick.SpringWeb.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@SpringBootApplication
public class SpringWebApplication implements CommandLineRunner {



	public static void main(String[] args) {
		SpringApplication.run(SpringWebApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

	}
}
