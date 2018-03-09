package com.cashier.model.vo;

import com.cashier.model.Buyer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@RunWith(MockitoJUnitRunner.class)
public class BuyerVOTest {

    private static Validator validator;

    private BuyerVO buyerVO;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        this.buyerVO = new BuyerVO();
        buyerVO.setName("Name");
        buyerVO.setEmail("email");
        buyerVO.setCpf(12345678900L);
    }

    @Test
    public void nullEmailShouldFailValidation(){
        //GIVEN
        this.buyerVO.setEmail(null);

        //WHEN
        Set<ConstraintViolation<BuyerVO>> violations = validator.validate(buyerVO);

        //THEN
        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(2, violations.size());
        Assert.assertEquals("email", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void nullNameShouldFailValidation(){
        //GIVEN
        buyerVO.setName(null);

        //WHEN
        Set<ConstraintViolation<BuyerVO>> violations = validator.validate(buyerVO);

        //THEN
        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(2, violations.size());
        Assert.assertEquals("name", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void nullCpfShouldFailValidation(){
        //GIVEN
        buyerVO.setCpf(null);

        //WHEN
        Set<ConstraintViolation<BuyerVO>> violations = validator.validate(buyerVO);

        //THEN
        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("cpf", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void largerCpfShouldFailValidation(){
        //GIVEN
        buyerVO.setCpf(111111111111L);

        //WHEN
        Set<ConstraintViolation<BuyerVO>> violations = validator.validate(buyerVO);

        //THEN
        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("cpf", violations.iterator().next().getPropertyPath().toString());
    }
}
