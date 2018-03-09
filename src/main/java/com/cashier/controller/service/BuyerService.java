package com.cashier.controller.service;

import com.cashier.controller.repository.BuyerRepository;
import com.cashier.model.Buyer;
import com.cashier.model.vo.BuyerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuyerService {

    @Autowired
    private BuyerRepository buyerRepository;

    /**
     * Save a new buyer. Do nothing if buyer already exists.
     *
     * @param buyerVO the object that contains information to save
     * @return a new {@link Buyer} to be used.
     */
    public Buyer processBuyer(BuyerVO buyerVO) {
        Buyer buyer = this.buyerRepository.findByCpf(buyerVO.getCpf());
        if (buyer != null) {
            return buyer;
        }
        buyer = new Buyer();
        buyer.setName(buyerVO.getName());
        buyer.setEmail(buyerVO.getEmail());
        buyer.setCpf(buyerVO.getCpf());
        return this.buyerRepository.save(buyer);
    }
}
