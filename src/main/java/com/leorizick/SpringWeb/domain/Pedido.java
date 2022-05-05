package com.leorizick.SpringWeb.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
public class Pedido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;


    @OneToMany(mappedBy = "id.pedido")
    private Set<ItemPedido> itens = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
    private Pagamento pagamento;

    @ManyToOne
    @JoinColumn(name = "entrega_id")
    private Endereco enderecoDeEntrega;

    public Cliente getCliente() {
        return cliente;
    }

    public Pedido() {

    }

    public Pedido(Integer id, Date date, Cliente cliente, Endereco enderecoDeEntrega) {
        this.id = id;
        this.date = date;
        this.enderecoDeEntrega = enderecoDeEntrega;
        this.cliente = cliente;
    }

    public Set<ItemPedido> getItens() {
        return itens;
    }

    public double getValorTotal() {
        double soma = 0.0;
        for (ItemPedido ip : itens) {
            soma = soma + ip.getSubTotal();
        }
        return soma;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public Endereco getEnderecoDeEntrega() {
        return enderecoDeEntrega;
    }

    public void setEnderecoDeEntrega(Endereco enderecoDeEntrega) {
        this.enderecoDeEntrega = enderecoDeEntrega;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pedido)) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(getId(), pedido.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        sb.append("Pedido numero: ").append(id);
        sb.append(", Instante: ").append(sdf.format(date)).append("\n");
        sb.append("Cliente: ").append(cliente.getName());
        sb.append(", Situacao do pagamento:").append(pagamento.getEstado().getDescricao());
        sb.append("\n");
        sb.append("Detalhes: ").append("\n");
        for (ItemPedido ip : getItens()){
            sb.append(ip.toString());
        }
        sb.append("Valor total: ").append(nf.format(getValorTotal())).append("\n");
        sb.append("Endereco de entrega: ").append(enderecoDeEntrega.toString());
        return sb.toString();
    }
}

