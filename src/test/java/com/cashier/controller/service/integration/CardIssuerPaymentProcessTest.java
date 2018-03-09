package com.cashier.controller.service.integration;

import com.cashier.controller.repository.PaymentRepository;
import com.cashier.model.Card;
import com.cashier.model.Payment;
import com.cashier.model.PaymentStatus;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CardIssuerPaymentProcessTest {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private ValidateCardWithCardIssuer validateCardWithCardIssuer;

    @Mock
    private IssuerFinderComponent issuerFinderComponent;

    @InjectMocks
    private CardIssuerPaymentProcess cardIssuerPaymentProcess;

    @Test
    public void shouldSetErrorStatusBecauseCardIssuerNotFound(){
        //GIVEN
        when(this.issuerFinderComponent.findIssuer(any(Card.class))).thenReturn(null);

        Payment payment = new Payment();

        //WHEN
        this.cardIssuerPaymentProcess.process(payment);

        //THEN
        assertEquals(PaymentStatus.ISSUER_NOT_FOUND_ERROR, payment.getStatus());
        verify(this.paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    public void shouldSetTheStatusThatIsReturned(){
        //GIVEN
        when(this.issuerFinderComponent.findIssuer(any(Card.class))).thenReturn(CardIssuer.VISA);
        when(this.validateCardWithCardIssuer.validate(any(CardIssuer.class), any(Card.class))).thenReturn(PaymentStatus.APPROVED);
        Payment payment = new Payment();

        //WHEN
        this.cardIssuerPaymentProcess.process(payment);

        //THEN
        assertEquals(PaymentStatus.APPROVED, payment.getStatus());
        verify(this.paymentRepository, times(1)).save(any(Payment.class));
    }
}

