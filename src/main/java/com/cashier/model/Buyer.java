package com.cashier.model;

import javax.persistence.*;

@Entity
@Table(name = "BUYER")
@SequenceGenerator(name = "SEQ_BUYER", sequenceName = "SEQ_BUYER")
public class Buyer {

    @Id
    @GeneratedValue(generator = "SEQ_BUYER", strategy = GenerationType.SEQUENCE)
    @Column(name = "BUYER_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "CPF")
    private Long cpf;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }
}
