package com.cashier.controller.service.integration;

import com.cashier.model.Card;
import org.springframework.stereotype.Component;

/**
 * Responsible for find the CardIssuer
 */
@Component
public class IssuerFinderComponent {

    /**
     * Finds a {@link CardIssuer} by its code based on card number.
     *
     * @param card the card
     * @return a {@link CardIssuer}
     */
    public CardIssuer findIssuer(Card card) {
        Long number = card.getNumber();
        String substring = number.toString().substring(0, 2);
        int code = Integer.parseInt(substring);

        return CardIssuer.findCardIssuer(code);
    }
}
