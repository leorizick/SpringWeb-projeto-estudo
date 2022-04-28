package com.leorizick.SpringWeb.dto;

import com.leorizick.SpringWeb.domain.Categorias;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class CategoriasDTO {

    private Integer id;

    @NotBlank(message = "Preenchimento obrigatorio!")
    @Size(min = 5, max = 80, message = "O tamanho deve ser entre 5 e 80 caracteres")
    private String name;

    public CategoriasDTO() {

    }

    public CategoriasDTO(Categorias obj) {
        id = obj.getId();
        name = obj.getName();
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
}
