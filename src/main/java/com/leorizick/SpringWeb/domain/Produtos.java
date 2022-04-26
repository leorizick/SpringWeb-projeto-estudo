package com.leorizick.SpringWeb.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class Produtos implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Double price;

    @JsonIgnore
    @OneToMany(mappedBy = "id.produto")
    private Set<ItemPedido> itens = new HashSet<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "PRODUTOS_CATEGORIAS",
            joinColumns = @JoinColumn(name = "produtos_id"),
            inverseJoinColumns = @JoinColumn(name = "categorias_id"))
    private List<Categorias> categorias = new ArrayList<>();

    public Produtos(){

    }

    public Produtos(Integer id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }


    @JsonIgnore
    public Set<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(Set<ItemPedido> itens) {
        this.itens = itens;
    }

    @JsonIgnore
    public List<Pedido> getPedido(){
     List<Pedido> list = new ArrayList<>();
     for(ItemPedido x : itens){
         list.add(x.getPedido());
     }
     return list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @JsonIgnore
    public List<Categorias> getCategories() {
        return categorias;
    }

    @Override
    public String toString() {
        return "Produtos{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produtos)) return false;
        Produtos produtos = (Produtos) o;
        return Objects.equals(getId(), produtos.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
