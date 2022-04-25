package com.leorizick.SpringWeb;

import com.leorizick.SpringWeb.domain.Category;
import com.leorizick.SpringWeb.domain.City;
import com.leorizick.SpringWeb.domain.Product;
import com.leorizick.SpringWeb.domain.State;
import com.leorizick.SpringWeb.repositories.CategoryRepository;
import com.leorizick.SpringWeb.repositories.CityRepository;
import com.leorizick.SpringWeb.repositories.ProductRepository;
import com.leorizick.SpringWeb.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class SpringWebApplication implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private CityRepository cityRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringWebApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Category cat1 = new Category(null, "Informatica");
		Category cat2 = new Category(null, "Escritorio");

		Product p1 = new Product(null, "Computador", 2000.00);
		Product p2 = new Product(null, "Impressora", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);

		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2));

		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2));
		p3.getCategories().addAll(Arrays.asList(cat2));

		categoryRepository.saveAll(Arrays.asList(cat1, cat2));
		productRepository.saveAll(Arrays.asList(p1,p2,p3));

		State stat1 = new State(null, "Minas Gerais");
		State stat2 = new State(null, "São Paulo");

		City city1 = new City(null, "Uberlandia", stat1);
		City city2 = new City(null, "São Paulo", stat2);
		City city3 = new City(null,"Campinas", stat2);

		stat1.getCidades().addAll(Arrays.asList(city1));
		stat2.getCidades().addAll(Arrays.asList(city2, city3));

		stateRepository.saveAll(Arrays.asList(stat1, stat2));
		cityRepository.saveAll(Arrays.asList(city1,city2,city3));



	}
}
