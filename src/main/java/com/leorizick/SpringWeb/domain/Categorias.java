package com.leorizick.SpringWeb.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Categorias implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;


    @ManyToMany(mappedBy = "categorias")
    private List<Produtos> produtos = new ArrayList<>();

    public Categorias() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Categorias(Integer id, String name) {
        this.id = id;
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Categorias{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Categorias)) return false;
        Categorias categorias = (Categorias) o;
        return Objects.equals(getId(), categorias.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public List<Produtos> getProducts() {
        return produtos;
    }

}
