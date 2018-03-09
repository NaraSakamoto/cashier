package com.cashier.controller.service;

import com.cashier.controller.repository.ClientRepository;
import com.cashier.model.Client;
import com.cashier.model.vo.ClientVO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    @Test(expected = RuntimeException.class)
    public void shouldThrowAnExceptionBecauseClientIsNull() {
        //GIVEN
        Mockito.when(clientRepository.findOne(Matchers.anyLong())).thenReturn(null);

        //WHEN
        this.clientService.processClient(new ClientVO());

        //THEN - throw exception
    }

    @Test
    public void shouldReturnTheClientThatWasFound(){
        //GIVEN
        Client client = new Client();
        Mockito.when(clientRepository.findOne(Matchers.anyLong())).thenReturn(client);

        //WHEN
        Client result = this.clientService.processClient(new ClientVO());

        //THEN
        Assert.assertEquals(client, result);
    }
}
