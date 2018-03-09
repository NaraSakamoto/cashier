package com.cashier.model.vo;

import com.cashier.model.Card;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.Set;

@RunWith(MockitoJUnitRunner.class)
public class CardVOTest {

    private static Validator validator;

    private CardVO cardVO = new CardVO();

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        this.cardVO = new CardVO();
        cardVO.setHolderName("holder's name");
        cardVO.setNumber(1234L);
        cardVO.setCvv(123);
        cardVO.setExpirationDate(LocalDate.now());

    }

    @Test
    public void nullHolderNameShouldFailValidation() {
        //GIVEN
        cardVO.setHolderName(null);

        //WHEN
        Set<ConstraintViolation<CardVO>> violations = validator.validate(cardVO);

        //THEN
        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("holderName", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void nullNumberShouldFailValidation() {
        //GIVEN
        cardVO.setNumber(null);

        //WHEN
        Set<ConstraintViolation<CardVO>> violations = validator.validate(cardVO);

        //THEN
        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("number", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void nullCvvShouldFailValidation() {
        //GIVEN
        cardVO.setCvv(null);

        //WHEN
        Set<ConstraintViolation<CardVO>> violations = validator.validate(cardVO);

        //THEN
        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("cvv", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void nullExpirationDateShouldFailValidation() {
        //GIVEN
        cardVO.setExpirationDate(null);

        //WHEN
        Set<ConstraintViolation<CardVO>> violations = validator.validate(cardVO);

        //THEN
        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("expirationDate", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void largerCvvShouldFailValidation() {
        //GIVEN
        cardVO.setCvv(1234);

        //WHEN
        Set<ConstraintViolation<CardVO>> violations = validator.validate(cardVO);

        //THEN
        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("cvv", violations.iterator().next().getPropertyPath().toString());
    }
}
