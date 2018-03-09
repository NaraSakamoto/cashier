package com.cashier.controller.service.strategy;

import com.cashier.controller.repository.PaymentRepository;
import com.cashier.controller.service.CardService;
import com.cashier.controller.service.integration.CardIssuerPaymentProcess;
import com.cashier.model.Card;
import com.cashier.model.Payment;
import com.cashier.model.PaymentStatus;
import com.cashier.model.vo.CardVO;
import com.cashier.model.vo.PaymentVO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class CreditCardPaymentStrategyTest {

    @Mock
    private CardService cardService;

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private CardIssuerPaymentProcess cardIssuerPaymentProcess;

    @InjectMocks
    private CreditCardPaymentStrategy creditCardPaymentStrategy;

    @Test
    public void shouldCallDependenciesInOrder(){
        //GIVEN
        Mockito.when(this.cardService.processCard(Matchers.any(CardVO.class))).thenReturn(new Card());

        InOrder inOrder = Mockito.inOrder(cardService, paymentRepository, cardIssuerPaymentProcess);

        //WHEN
        this.creditCardPaymentStrategy.processPayment(new PaymentVO(), new Payment());

        //THEN
        inOrder.verify(cardService, times(1)).processCard(Matchers.any(CardVO.class));
        inOrder.verify(paymentRepository, times(1)).save(Matchers.any(Payment.class));
        inOrder.verify(cardIssuerPaymentProcess, times(1)).process(Matchers.any(Payment.class));
    }

    @Test
    public void shouldReturnProcessingStatus(){
        //GIVEN
        Mockito.when(this.cardService.processCard(Matchers.any(CardVO.class))).thenReturn(new Card());

        //WHEN
        String status = this.creditCardPaymentStrategy.processPayment(new PaymentVO(), new Payment());

        //THEN
        Assert.assertTrue(status.contains(PaymentStatus.PROCESSING.name()));
    }

}
