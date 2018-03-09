package com.cashier.model;

import javax.persistence.*;

@Entity
@Table(name = "CLIENT")
@SequenceGenerator(name = "SEQ_CLIENT", sequenceName = "SEQ_CLIENT")
public class Client {

    @Id
    @GeneratedValue(generator = "SEQ_CLIENT", strategy = GenerationType.SEQUENCE)
    @Column(name = "CLIENT_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

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
}
