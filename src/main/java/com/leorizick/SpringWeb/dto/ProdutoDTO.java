package com.leorizick.SpringWeb.dto;

import com.leorizick.SpringWeb.domain.Categorias;
import com.leorizick.SpringWeb.domain.Produto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class ProdutoDTO {

    private Integer id;
    private Double price;

    @NotBlank(message = "Preenchimento obrigatorio!")
    @Size(min = 5, max = 80, message = "O tamanho deve ser entre 5 e 80 caracteres")
    private String name;

    public ProdutoDTO() {

    }

    public ProdutoDTO(Produto obj) {
        id = obj.getId();
        name = obj.getName();
        price = obj.getPrice();
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
}
