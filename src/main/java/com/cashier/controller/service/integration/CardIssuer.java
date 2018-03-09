package com.cashier.controller.service.integration;

/**
 * Represents de Card Issuers
 */
public enum CardIssuer {

    VISA(41),

    MASTER(51),

    AMERICAN_EXPRESS(37);

    private int code;

    CardIssuer(int code) {
        this.code = code;
    }

    /**
     * Find a {@link CardIssuer} by the code
     * @param code the code to check
     * @return a {@link CardIssuer}
     */
    public static CardIssuer findCardIssuer(int code) {
        for (CardIssuer cardIssuer : CardIssuer.values()) {
            if (cardIssuer.code == code) {
                return cardIssuer;
            }
        }
        return null;
    }
}