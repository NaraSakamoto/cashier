package com.cashier.ui;

import com.cashier.controller.exception.ClientNotFoundException;
import com.cashier.controller.repository.ClientRepository;
import com.cashier.controller.service.PaymentService;
import com.cashier.model.Client;
import com.cashier.model.PaymentType;
import com.cashier.model.vo.TransactionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping(value = "/payments")
@Controller
public class UiController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private ClientRepository clientRepository;

    @RequestMapping(method = RequestMethod.GET, value = "{clientId}")
    public String init(@PathVariable Long clientId, Model model) {

        Client one = this.clientRepository.findOne(clientId);
        if (one == null) {
            model.addAttribute("erros", Arrays.asList("Client not found"));
        } else {
            model.addAttribute("client", one);
        }
        model.addAttribute("types", PaymentType.values());
        return "checkout";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createPayment(@Valid @ModelAttribute TransactionVO transactionVO, BindingResult errors, Model model) {
        model.addAttribute("types", PaymentType.values());
        model.addAttribute("client", transactionVO.getClient());

        if (errors.hasErrors()) {
            List<ObjectError> allErrors = errors.getAllErrors();
            List<String> stringErros = allErrors.stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());
            model.addAttribute("errors", stringErros);
            return "checkout";
        }

        try {
            String result = this.paymentService.processTransaction(transactionVO);
            model.addAttribute("result", result);
            return "thanks";
        } catch (ClientNotFoundException e) {
            model.addAttribute("errors", Arrays.asList(e.getMessage()));
            return "checkout";
        }
    }
}
