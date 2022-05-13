package com.leorizick.SpringWeb.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class EmailDTO implements Serializable {

    private String email;

    @NotBlank(message = "Preenchimento obrigatorio!")
    @Email(message = "Email invalido!")
    public EmailDTO(){

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
