package com.cashier.controller.service;

import com.cashier.controller.repository.CardRepository;
import com.cashier.model.Card;
import com.cashier.model.vo.CardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    /**
     * Save a new card. It doesn't do nothing if card already exists.
     *
     * @param cardVO the object that contains information to save.
     * @return a {@link Card} to be used.
     */
    public Card processCard(CardVO cardVO) {
        Card card = cardRepository.findByNumber(cardVO.getNumber());
        if (card != null) {
            return card;
        }

        card = new Card();
        card.setHolderName(cardVO.getHolderName());
        card.setNumber(cardVO.getNumber());
        card.setExpirationDate(cardVO.getExpirationDate());
        card.setCvv(cardVO.getCvv());
        return this.cardRepository.save(card);
    }
}
