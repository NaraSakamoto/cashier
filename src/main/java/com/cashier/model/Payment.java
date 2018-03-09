package com.cashier.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "PAYMENT")
@SequenceGenerator(name = "SEQ_PAYMNT", sequenceName = "SEQ_PAYMNT")
public class Payment {

    @Id
    @GeneratedValue(generator = "SEQ_PAYMNT", strategy = GenerationType.SEQUENCE)
    @Column(name = "PAYMENT_ID")
    private Long id;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "TYPE")
    private PaymentType type;

    @ManyToOne
    @JoinColumn(name = "BUYER_ID")
    private Buyer buyer;

    @ManyToOne
    @JoinColumn(name = "CARD_ID")
    private Card card;

    @ManyToOne
    @JoinColumn(name = "CLIENT_ID")
    private Client client;

    @Column(name = "STATUS")
    private PaymentStatus status;

    @Column(name = "BOLETO_NUMBER")
    private Long boletoNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public PaymentType getType() {
        return type;
    }

    public void setType(PaymentType type) {
        this.type = type;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public Long getBoletoNumber() {
        return boletoNumber;
    }

    public void setBoletoNumber(Long boletoNumber) {
        this.boletoNumber = boletoNumber;
    }
}

