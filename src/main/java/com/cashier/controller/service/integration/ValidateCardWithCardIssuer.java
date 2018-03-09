package com.cashier.controller.service.integration;

import com.cashier.model.Card;
import com.cashier.model.PaymentStatus;
import org.springframework.stereotype.Component;

@Component
public class ValidateCardWithCardIssuer {

    public PaymentStatus validate(CardIssuer issuer, Card card){
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            //nothing
        }
        return Math.random() < 0.5 ? PaymentStatus.APPROVED : PaymentStatus.UNAUTHORIZED;
    }
}
