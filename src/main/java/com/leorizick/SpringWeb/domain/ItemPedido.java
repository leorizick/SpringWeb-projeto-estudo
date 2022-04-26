package com.leorizick.SpringWeb.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class ItemPedido implements Serializable {

    @EmbeddedId
    @JsonIgnore
    private ItemPedidoPk id = new ItemPedidoPk();

    private Double desconto;
    private Integer quantidade;
    private Double preco;

    public ItemPedido() {

    }

    public ItemPedido( Pedido pedido, Produtos produto, Double desconto, Integer quantidade, Double preco) {
        this.id.setPedido(pedido);
        this.id.setProduto(produto);
        this.desconto = desconto;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public Produtos getProdutos() {
        return id.getProduto();
    }

    @JsonIgnore
    public Pedido getPedido() {
        return id.getPedido();
    }

    public ItemPedidoPk getId() {
        return id;
    }

    public void setId(ItemPedidoPk id) {
        this.id = id;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemPedido)) return false;
        ItemPedido that = (ItemPedido) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
