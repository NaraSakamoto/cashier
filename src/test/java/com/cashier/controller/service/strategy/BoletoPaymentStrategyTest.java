package com.cashier.controller.service.strategy;

import com.cashier.controller.repository.PaymentRepository;
import com.cashier.model.Payment;
import com.cashier.model.vo.PaymentVO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BoletoPaymentStrategyTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private BoletoPaymentStrategy boletoPaymentStrategy;

    @Test
    public void shouldReturnABoletoNumber(){
        //GIVEN
        Payment payment = new Payment();

        //WHEN
        String result = this.boletoPaymentStrategy.processPayment(new PaymentVO(), payment);

        //THEN
        Assert.assertTrue(result.contains(payment.getBoletoNumber().toString()));
    }
}
