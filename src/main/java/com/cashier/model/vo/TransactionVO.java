package com.cashier.model.vo;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class TransactionVO {

    @Valid
    @NotNull
    private BuyerVO buyer;

    @Valid
    @NotNull
    private ClientVO client;

    @Valid
    @NotNull
    private PaymentVO payment;

    public BuyerVO getBuyer() {
        return buyer;
    }

    public void setBuyer(BuyerVO buyer) {
        this.buyer = buyer;
    }

    public ClientVO getClient() {
        return client;
    }

    public void setClient(ClientVO client) {
        this.client = client;
    }

    public PaymentVO getPayment() {
        return payment;
    }

    public void setPayment(PaymentVO payment) {
        this.payment = payment;
    }
}
