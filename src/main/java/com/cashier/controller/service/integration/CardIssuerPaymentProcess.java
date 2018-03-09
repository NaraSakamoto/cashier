package com.cashier.controller.service.integration;

import com.cashier.controller.repository.PaymentRepository;
import com.cashier.model.Payment;
import com.cashier.model.PaymentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * This class asynchronously process the payment integrating with card issuer
 */
@Component
public class CardIssuerPaymentProcess {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ValidateCardWithCardIssuer validateCardWithCardIssuer;

    @Autowired
    private IssuerFinderComponent issuerFinderComponent;

    /**
     * Process the payment in an asynchronously process
     * @param payment the {@link Payment} to process
     */
    @Async
    public void process(Payment payment){
        CardIssuer cardIssuer = issuerFinderComponent.findIssuer(payment.getCard());
        if(cardIssuer == null){
            payment.setStatus(PaymentStatus.ISSUER_NOT_FOUND_ERROR);
            paymentRepository.save(payment);
            return;
        }

        PaymentStatus status = this.validateCardWithCardIssuer.validate(cardIssuer, payment.getCard());
        payment.setStatus(status);
        paymentRepository.save(payment);
    }
}
