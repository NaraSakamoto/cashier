package com.cashier.controller.service.strategy;

import com.cashier.controller.repository.PaymentRepository;
import com.cashier.controller.service.CardService;
import com.cashier.controller.service.integration.CardIssuerPaymentProcess;
import com.cashier.model.Card;
import com.cashier.model.Payment;
import com.cashier.model.PaymentStatus;
import com.cashier.model.vo.PaymentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * This class represents a way to process credit card payments.
 */
@Component
public class CreditCardPaymentStrategy implements PaymentStrategy {

    @Autowired
    private CardService cardService;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private CardIssuerPaymentProcess cardIssuerPaymentProcess;

    /**
     * Process a credit card payment.
     *
     * @param paymentVO the object that contains card not processed information
     * @param payment the payment to process
     * @return a String that contains a status
     */
    @Override
    public String processPayment(PaymentVO paymentVO, Payment payment) {
        Card card = this.cardService.processCard(paymentVO.getCard());
        payment.setCard(card);
        payment.setStatus(PaymentStatus.PROCESSING);
        this.paymentRepository.save(payment);

        this.cardIssuerPaymentProcess.process(payment);

        return "Credit card process status: " + payment.getStatus().name();
    }
}
