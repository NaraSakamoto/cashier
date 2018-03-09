package com.cashier.controller.service;

import com.cashier.controller.repository.PaymentRepository;
import com.cashier.model.Payment;
import com.cashier.model.PaymentStatus;
import com.cashier.model.vo.BuyerVO;
import com.cashier.model.vo.ClientVO;
import com.cashier.model.vo.PaymentVO;
import com.cashier.model.vo.TransactionVO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.ArgumentCaptor.forClass;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PaymentServiceTest {

    @Mock
    private BuyerService buyerService;

    @Mock
    private ClientService clientService;

    @Mock
    private PaymentProcessComponent paymentProcessComponent;

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentService paymentService;

    @Test
    public void shouldSaveAPaymentWithProcessingStatus(){
        //GIVEN
        TransactionVO transactionVO = new TransactionVO();
        transactionVO.setPayment(new PaymentVO());

        ArgumentCaptor<Payment> captor = forClass(Payment.class);
        when(paymentRepository.save(captor.capture())).thenReturn(new Payment());

        //WHEN
        paymentService.processTransaction(transactionVO);

        //THEN
        Assert.assertEquals(PaymentStatus.PROCESSING, captor.getValue().getStatus());
    }

    @Test
    public void shouldCallDependenciesInOrder(){
        //GIVEN
        BuyerVO buyerVO = new BuyerVO();
        ClientVO clientVO = new ClientVO();
        PaymentVO paymentVO = new PaymentVO();
        TransactionVO transactionVO = new TransactionVO();
        transactionVO.setBuyer(buyerVO);
        transactionVO.setClient(clientVO);
        transactionVO.setPayment(paymentVO);

        InOrder inOrder = Mockito.inOrder(buyerService, clientService, paymentRepository, paymentProcessComponent);

        //WHEN
        this.paymentService.processTransaction(transactionVO);

        //THEN
        inOrder.verify(buyerService, times(1)).processBuyer(buyerVO);
        inOrder.verify(clientService, times(1)).processClient(clientVO);

        inOrder.verify(paymentRepository, times(1)).save(Matchers.any(Payment.class));
        inOrder.verify(paymentProcessComponent, times(1)).processPayment(Matchers.eq(paymentVO), Matchers.any(Payment.class));
    }
}
