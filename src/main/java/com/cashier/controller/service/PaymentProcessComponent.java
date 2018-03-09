package com.cashier.controller.service;

import com.cashier.controller.repository.PaymentRepository;
import com.cashier.controller.service.strategy.BoletoPaymentStrategy;
import com.cashier.controller.service.strategy.CreditCardPaymentStrategy;
import com.cashier.controller.service.strategy.PaymentStrategy;
import com.cashier.model.*;
import com.cashier.model.vo.PaymentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PaymentProcessComponent {

    private PaymentRepository paymentRepository;

    private Map<PaymentType, PaymentStrategy> strategyMap;

    /**
     * Processes the payment according to the payment type
     *
     * @param creditCardPaymentStrategy
     * @param boletoPaymentStrategy
     * @param paymentRepository
     */
    @Autowired
    public PaymentProcessComponent(CreditCardPaymentStrategy creditCardPaymentStrategy, BoletoPaymentStrategy boletoPaymentStrategy, PaymentRepository paymentRepository) {
        this.strategyMap = new HashMap<>();
        this.strategyMap.put(PaymentType.CREDIT_CARD, creditCardPaymentStrategy);
        this.strategyMap.put(PaymentType.BOLETO, boletoPaymentStrategy);

        this.paymentRepository = paymentRepository;
    }

    public String processPayment(PaymentVO paymentVO, Payment payment) {
        PaymentStrategy paymentStrategy = this.strategyMap.get(paymentVO.getType());

        return paymentStrategy.processPayment(paymentVO, payment);
    }
}
