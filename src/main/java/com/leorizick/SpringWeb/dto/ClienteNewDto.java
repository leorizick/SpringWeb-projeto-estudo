package com.leorizick.SpringWeb.dto;

import com.leorizick.SpringWeb.services.validation.ClienteInsert;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@ClienteInsert
public class ClienteNewDto implements Serializable {

    @NotBlank(message = "Preenchimento obrigatorio")
    @Size(min = 5, max = 80, message = "O nome deve conter entre 5 e 80 caracteres")
    private String name;

    @NotBlank(message = "Preenchimento obrigatorio")
    @Email(message = "Email invalido")
    private String email;

    @NotBlank(message = "Preenchimento obrigatorio")
    private String documento;


    private Integer tipo;

    @NotBlank(message = "Preenchimento obrigatorio")
    private String logradouro;
    private String numero;
    private String complemento;


    private String bairro;
    private String cep;

    private Integer cidadeId;

    @NotBlank(message = "Preenchimento obrigatorio")
    private String telefone1;
    private String telefone2;
    private String telefone3;

    @NotBlank(message = "Preenchimento obrigatorio")
    private String senha;


    public ClienteNewDto(){

    }

    public ClienteNewDto(String name, String email, String documento, Integer tipo, String logradouro, String numero, String complemento, String bairro, String cep, Integer cidadeId) {
        this.name = name;
        this.email = email;
        this.documento = documento;
        this.tipo = tipo;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cep = cep;
        this.cidadeId = cidadeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Integer getCidadeId() {
        return cidadeId;
    }

    public void setCidadeId(Integer cidadeId) {
        this.cidadeId = cidadeId;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public String getTelefone3() {
        return telefone3;
    }

    public void setTelefone3(String telefone3) {
        this.telefone3 = telefone3;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}



