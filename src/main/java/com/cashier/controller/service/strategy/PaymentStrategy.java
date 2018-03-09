package com.cashier.controller.service.strategy;

import com.cashier.model.Payment;
import com.cashier.model.vo.PaymentVO;

public interface PaymentStrategy {

    String processPayment(PaymentVO paymentVO, Payment payment);
}
