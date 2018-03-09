package com.cashier.controller.service;

import com.cashier.controller.repository.PaymentRepository;
import com.cashier.controller.service.strategy.BoletoPaymentStrategy;
import com.cashier.controller.service.strategy.CreditCardPaymentStrategy;
import com.cashier.controller.service.strategy.CreditCardPaymentStrategyTest;
import com.cashier.model.Buyer;
import com.cashier.model.Client;
import com.cashier.model.Payment;
import com.cashier.model.PaymentType;
import com.cashier.model.vo.PaymentVO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PaymentProcessComponentTest {

    @Mock
    private CreditCardPaymentStrategy creditCardPaymentStrategy;

    @Mock
    private BoletoPaymentStrategy boletoPaymentStrategy;

    @Mock
    private PaymentRepository paymentRepository;

    private PaymentProcessComponent paymentProcessComponent;

    @Before
    public void setUp() {
        this.paymentProcessComponent = new PaymentProcessComponent(creditCardPaymentStrategy, boletoPaymentStrategy, paymentRepository);
    }

    @Test
    public void shouldCallBoletoStrategyBecauseTypeIsBoleto() {
        //GIVEN
        PaymentVO payment = new PaymentVO();
        payment.setType(PaymentType.BOLETO);

        when(this.boletoPaymentStrategy.processPayment(any(PaymentVO.class), any(Payment.class))).thenReturn("1234567890");

        //WHEN
        String result = this.paymentProcessComponent.processPayment(payment, new Payment());

        //THEN
        verify(this.boletoPaymentStrategy, atLeast(1)).processPayment(any(PaymentVO.class), any(Payment.class));
        verify(this.creditCardPaymentStrategy, never()).processPayment(any(PaymentVO.class), any(Payment.class));
        assertEquals("1234567890", result);
    }

    @Test
    public void shouldCallCreditCardStrategyBecauseTypeIsCreditCard() {
        //GIVEN
        PaymentVO payment = new PaymentVO();
        payment.setType(PaymentType.CREDIT_CARD);

        when(this.creditCardPaymentStrategy.processPayment(any(PaymentVO.class), any(Payment.class))).thenReturn("SUCCESS");

        //WHEN
        String result = this.paymentProcessComponent.processPayment(payment, new Payment());

        //THEN
        verify(this.creditCardPaymentStrategy, atLeast(1)).processPayment(any(PaymentVO.class), any(Payment.class));
        verify(this.boletoPaymentStrategy, never()).processPayment(any(PaymentVO.class), any(Payment.class));
        assertEquals("SUCCESS", result);
    }

}
