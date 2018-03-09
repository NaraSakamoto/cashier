package com.cashier.controller.service;

import com.cashier.controller.exception.ClientNotFoundException;
import com.cashier.controller.repository.ClientRepository;
import com.cashier.model.Client;
import com.cashier.model.vo.ClientVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    /**
     * It finds a client by id. Throws exception if client was not found
     * @param clientVO
     * @return
     */
    public Client processClient(ClientVO clientVO) {
        Client client = clientRepository.findOne(clientVO.getId());
        if (client == null) {
            throw new ClientNotFoundException("Client with id " + clientVO.getId() + " not found");
        }
        return client;
    }
}