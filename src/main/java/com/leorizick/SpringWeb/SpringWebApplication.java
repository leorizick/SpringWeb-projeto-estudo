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

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	EnderecoRepository enderecoRepository;
	@Autowired
	PedidoRepository pedidoRepository;
	@Autowired
	PagamentoRepository pagamentoRepository;
	@Autowired
	ItemPedidoRepository itemPedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringWebApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categorias cat1 = new Categorias(null, "Informatica");
		Categorias cat2 = new Categorias(null, "Escritorio");
		Categorias cat3 = new Categorias(null, "Jantar");
		Categorias cat4 = new Categorias(null, "Jogos");
		Categorias cat5 = new Categorias(null, "Banheiros");
		Categorias cat6 = new Categorias(null, "Departamento");


		Produtos p1 = new Produtos(null, "Computador", 2000.00);
		Produtos p2 = new Produtos(null, "Impressora", 800.00);
		Produtos p3 = new Produtos(null, "Mouse", 80.00);

		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2));

		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2));
		p3.getCategories().addAll(Arrays.asList(cat2));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));

		Estado stat1 = new Estado(null, "Minas Gerais");
		Estado stat2 = new Estado(null, "São Paulo");

		Cidade cidade1 = new Cidade(null, "Uberlandia", stat1);
		Cidade cidade2 = new Cidade(null, "São Paulo", stat2);
		Cidade cidade3 = new Cidade(null,"Campinas", stat2);

		stat1.getCidades().addAll(Arrays.asList(cidade1));
		stat2.getCidades().addAll(Arrays.asList(cidade2, cidade3));

		estadoRepository.saveAll(Arrays.asList(stat1, stat2));
		cidadeRepository.saveAll(Arrays.asList(cidade1, cidade2, cidade3));

		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("9989777866", "997327417"));

		Endereco end1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", cli1, cidade1);
		Endereco end2 = new Endereco(null, "Avenida matos", "105", "Sala 800", "Centro", "38777012", cli1, cidade2);

		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));

		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(end1, end2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy HH:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 09:32"), cli1, end1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, end2);

		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);

		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 10:40"),null);
		ped2.setPagamento(pagto2);


		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));


		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00 );
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));

		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}

}
