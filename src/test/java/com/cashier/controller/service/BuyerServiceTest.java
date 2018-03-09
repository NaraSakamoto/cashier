package com.cashier.controller.service;

import com.cashier.controller.repository.BuyerRepository;
import com.cashier.model.Buyer;
import com.cashier.model.vo.BuyerVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class BuyerServiceTest {

    @Mock
    private BuyerRepository buyerRepository;

    @InjectMocks
    private BuyerService buyerService;

    @Test
    public void shouldReturnBuyerReturnedByRepository() {
        //GIVEN
        Long cpf = 1234L;
        String email = "mail@email.com";
        String name = "Name";

        BuyerVO buyerVO = new BuyerVO();
        buyerVO.setCpf(cpf);

        Buyer buyer = new Buyer();
        buyer.setCpf(cpf);
        buyer.setEmail(email);
        buyer.setName(name);

        Mockito.when(buyerRepository.findByCpf(Matchers.eq(cpf))).thenReturn(buyer);

        //WHEN
        Buyer processBuyer = buyerService.processBuyer(buyerVO);

        //THEN
        assertEquals(cpf, processBuyer.getCpf());
        assertEquals(email, processBuyer.getEmail());
        assertEquals(name, processBuyer.getName());

        Mockito.verify(buyerRepository, Mockito.times(0)).save(Matchers.any(Buyer.class));
    }

    @Test
    public void shouldSaveANewBuyer() {
        //GIVEN
        Long cpf = 1234L;
        String email = "mail@email.com";
        String name = "Name";

        BuyerVO buyerVO = new BuyerVO();
        buyerVO.setCpf(cpf);
        buyerVO.setEmail(email);
        buyerVO.setName(name);

        Mockito.when(buyerRepository.findByCpf(Matchers.eq(cpf))).thenReturn(null);

        //WHEN
        buyerService.processBuyer(buyerVO);

        //THEN
        Mockito.verify(buyerRepository, Mockito.times(1)).save(Matchers.any(Buyer.class));
    }
}