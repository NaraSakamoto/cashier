package com.cashier.model.validator;

import com.cashier.model.Card;
import com.cashier.model.Payment;
import com.cashier.model.PaymentType;
import com.cashier.model.vo.CardVO;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;
import java.lang.reflect.Field;

public class NotNullWithCreditCardValidator implements ConstraintValidator<NotNullWithCreditCard, Object> {

    private String paymentTypeFieldName;

    private String cardFieldName;

    @Override
    public void initialize(NotNullWithCreditCard constraintAnnotation) {
        this.paymentTypeFieldName = constraintAnnotation.paymentType();
        this.cardFieldName = constraintAnnotation.card();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {
        try {
            Object paymentType = this.getFieldValue(object, paymentTypeFieldName);
            Object card = this.getFieldValue(object, cardFieldName);

            if (paymentType == null) {
                return true;
            }

            PaymentType type = (PaymentType) paymentType;
            if (PaymentType.BOLETO.equals(type)) {
                return true;
            }

            if (PaymentType.CREDIT_CARD.equals(type)) {
                return card != null ? true : false;
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Object getFieldValue(Object object, String fieldName)  {
        Class<?> clazz = object.getClass();
        Field field = null;
        try {
            field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(object);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new IllegalArgumentException(
                    "Illegal class, expected one acessible attribute of type PaymentType and one attribute of type Card.");
        }
    }
}