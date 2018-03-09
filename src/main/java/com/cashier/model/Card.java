package com.cashier.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="CARD")
@SequenceGenerator(name = "SEQ_CARD", sequenceName = "SEQ_CARD")
public class Card {

    @Id
    @GeneratedValue(generator = "SEQ_CARD", strategy = GenerationType.SEQUENCE)
    @Column(name = "CARD_ID")
    private Long id;

    @Column(name = "NUMBER")
    private Long number;

    @Column(name = "HOLDER_NAME")
    private String holderName;

    @Column(name = "EXPIRATION_DATE")
    private LocalDate expirationDate;

    @Column(name = "CVV")
    private Integer cvv;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Integer getCvv() {
        return cvv;
    }

    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }
}
