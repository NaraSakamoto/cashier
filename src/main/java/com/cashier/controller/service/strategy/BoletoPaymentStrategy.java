package com.cashier.controller.service.strategy;

import com.cashier.controller.repository.PaymentRepository;
import com.cashier.model.Payment;
import com.cashier.model.vo.PaymentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This class represents the way that a boleto have to be processed.
 */
@Component
public class BoletoPaymentStrategy implements PaymentStrategy {

    @Autowired
    private PaymentRepository paymentRepository;

    /**
     * This is a mock method that generates a random number that represents a boleto number.
     * @param paymentVO
     * @param payment the payment that will be processed.
     * @return a String containing the boleto number.
     */
    @Override
    public String processPayment(PaymentVO paymentVO, Payment payment) {
        Long boletoNumber = (long) (1000000000000L + (Math.random() * 8999999999999L));

        payment.setBoletoNumber(boletoNumber);

        this.paymentRepository.save(payment);

        return "Boleto number: " + boletoNumber.toString();
    }
}
