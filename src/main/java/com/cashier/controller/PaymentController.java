package com.cashier.controller;

import com.cashier.controller.service.PaymentService;
import com.cashier.model.Payment;
import com.cashier.model.vo.TransactionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public String createPayment(@Valid @RequestBody TransactionVO transactionVO) {
        return this.paymentService.processTransaction(transactionVO);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Payment findAPaymentById(@PathVariable("id") Long id) {
        return paymentService.findById(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Payment> findAllPayments() {
        return paymentService.findAll();
    }

}
