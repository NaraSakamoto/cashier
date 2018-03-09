package com.cashier.model.vo;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

public class BuyerVO {

    @NotNull(message="Buyer name can't be null")
    @NotEmpty(message="Buyer name can't be empty")
    private String name;

    @NotNull(message="Buyer email can't be null")
    @NotEmpty(message="Buyer email can't be empty")
    private String email;

    @NotNull(message="Buyer cpf can't be null")
    @Max(99999999999L)
    private Long cpf;

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

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public boolean deveSalvar(String name, String email, Long cpf){
        return this.name.equalsIgnoreCase(name) &&
                this.email.equalsIgnoreCase(email) &&
                this.cpf.equals(cpf);
    }
}
