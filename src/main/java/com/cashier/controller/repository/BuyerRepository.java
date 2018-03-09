package com.cashier.controller.repository;

import com.cashier.model.Buyer;
import org.springframework.data.repository.CrudRepository;

public interface BuyerRepository extends CrudRepository<Buyer, Long> {

    Buyer findByCpf(Long cpf);
}
