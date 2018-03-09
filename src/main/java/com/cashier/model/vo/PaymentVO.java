package com.cashier.model.vo;

import com.cashier.model.PaymentType;
import com.cashier.model.validator.NotNullWithCreditCard;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@NotNullWithCreditCard(message = "Card can't be null")
public class PaymentVO {

    @NotNull(message = "Payment amount can't be null")
    private BigDecimal amount;

    @NotNull(message = "Payment type can't be null")
    private PaymentType type;

    @Valid
    private CardVO card;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public PaymentType getType() {
        return type;
    }

    public void setType(PaymentType type) {
        this.type = type;
    }

    public CardVO getCard() {
        return card;
    }

    public void setCard(CardVO card) {
        this.card = card;
    }
}
