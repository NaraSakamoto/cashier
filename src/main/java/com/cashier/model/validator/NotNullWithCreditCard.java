package com.cashier.model.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = NotNullWithCreditCardValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NotNullWithCreditCard {

    String message() default
            "Card must not be null";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * @return The name os paymentType field
     */
    String paymentType() default "type";

    /**
     * @return The name of card field
     */
    String card() default "card";
}
