package com.leorizick.SpringWeb.dto;

import com.leorizick.SpringWeb.domain.Categorias;

public class CategoriasDTO {

    private Integer id;
    private String name;

    public CategoriasDTO(){

    }

    public CategoriasDTO(Categorias obj){
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
