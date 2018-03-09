package com.cashier.controller.service.integration;

import com.cashier.model.Card;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class IssuerFinderComponentTest {

    @InjectMocks
    private IssuerFinderComponent issuerFinderComponent;

    @Test
    public void shouldReturnVisaBecausCardNumberStartsWith41(){
        //GIVEN
        Card card = new Card();
        card.setNumber(412367899769L);

        //WHEN
        CardIssuer cardIssuer = this.issuerFinderComponent.findIssuer(card);

        //THEN
        Assert.assertEquals(CardIssuer.VISA, cardIssuer);
    }

    @Test
    public void shouldReturnMasterBecauseCardNumberStartsWith51(){
        //GIVEN
        Card card = new Card();
        card.setNumber(512367899769L);

        //WHEN
        CardIssuer cardIssuer = this.issuerFinderComponent.findIssuer(card);

        //THEN
        Assert.assertEquals(CardIssuer.MASTER, cardIssuer);
    }

    @Test
    public void shouldReturnAmericanExpressBecauseCardNumberStartsWith37(){
        //GIVEN
        Card card = new Card();
        card.setNumber(372367899769L);

        //WHEN
        CardIssuer cardIssuer = this.issuerFinderComponent.findIssuer(card);

        //THEN
        Assert.assertEquals(CardIssuer.AMERICAN_EXPRESS, cardIssuer);
    }

    @Test
    public void shouldReturnNull(){
        //GIVEN
        Card card = new Card();
        card.setNumber(2361837899769L);

        //WHEN
        CardIssuer cardIssuer = this.issuerFinderComponent.findIssuer(card);

        //THEN
        Assert.assertNull(cardIssuer);
    }
}
