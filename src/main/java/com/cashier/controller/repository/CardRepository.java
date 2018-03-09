package com.cashier.controller.repository;

import com.cashier.model.Card;
import org.springframework.data.repository.CrudRepository;

public interface CardRepository extends CrudRepository<Card, Long>{

    Card findByNumber(Long number);

}
