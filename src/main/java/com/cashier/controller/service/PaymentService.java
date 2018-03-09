package com.cashier.controller.service;

import com.cashier.controller.repository.PaymentRepository;
import com.cashier.model.Buyer;
import com.cashier.model.Client;
import com.cashier.model.Payment;
import com.cashier.model.PaymentStatus;
import com.cashier.model.vo.PaymentVO;
import com.cashier.model.vo.TransactionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private BuyerService buyerService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private PaymentProcessComponent paymentProcessComponent;

    @Autowired
    private PaymentRepository paymentRepository;

    public String processTransaction(TransactionVO transactionVO) {
        Buyer buyer = this.buyerService.processBuyer(transactionVO.getBuyer());
        Client client = this.clientService.processClient(transactionVO.getClient());

        Payment payment = this.createPayment(transactionVO.getPayment(), buyer, client);
        String result = this.paymentProcessComponent.processPayment(transactionVO.getPayment(), payment);

        return result;
    }

    private Payment createPayment(PaymentVO paymentVO, Buyer buyer, Client client){
        Payment payment = new Payment();
        payment.setAmount(paymentVO.getAmount());
        payment.setBuyer(buyer);
        payment.setClient(client);
        payment.setType(paymentVO.getType());
        payment.setStatus(PaymentStatus.PROCESSING);
        return this.paymentRepository.save(payment);
    }

    public Payment findById(Long id) {
        return this.paymentRepository.findOne(id);
    }

    public List<Payment> findAll() {
        return (List<Payment>) this.paymentRepository.findAll();
    }

}
