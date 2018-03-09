package com.cashier.model.vo;

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
public class ClientVOTest {

    private static Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void nullClientIdShouldFailValidation() {
        //GIVEN
        ClientVO clientVO = new ClientVO();
        clientVO.setId(null);

        //WHEN
        Set<ConstraintViolation<ClientVO>> violations = validator.validate(clientVO);

        //THEN
        Assert.assertFalse(violations.isEmpty());
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("id", violations.iterator().next().getPropertyPath().toString());
    }

}
