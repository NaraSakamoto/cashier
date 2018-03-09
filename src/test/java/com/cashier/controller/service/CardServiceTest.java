package com.cashier.controller.service;

import com.cashier.controller.repository.CardRepository;
import com.cashier.model.Card;
import com.cashier.model.vo.CardVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CardServiceTest {

    @Mock
    private CardRepository cardRepository;

    @InjectMocks
    private CardService cardService;

    @Test
    public void shouldReturnCardReturnedByRepository(){
        //GIVEN
        Long number = 1234L;
        String holder = "Holder";
        LocalDate localDate = LocalDate.parse("2018-03-03", DateTimeFormatter.ISO_DATE);

        CardVO cardVO = new CardVO();
        cardVO.setNumber(number);

        Card card = new Card();
        card.setHolderName(holder);
        card.setNumber(number);
        card.setExpirationDate(localDate);

        Mockito.when(cardRepository.findByNumber(number)).thenReturn(card);

        //WHEN
        Card processCard = cardService.processCard(cardVO);

        //THEN
        assertEquals(number, processCard.getNumber());
        assertEquals(holder, processCard.getHolderName());
        assertEquals(localDate, processCard.getExpirationDate());

        Mockito.verify(cardRepository, Mockito.times(0)).save(Matchers.any(Card.class));
    }

    @Test
    public void shouldSaveANewCard() {

        //GIVEN
        Long number = 1234L;
        String holder = "Holder";
        LocalDate localDate = LocalDate.parse("2018-03-03", DateTimeFormatter.ISO_DATE);

        CardVO cardVO = new CardVO();
        cardVO.setNumber(number);

        Card card = new Card();
        card.setHolderName(holder);
        card.setNumber(number);
        card.setExpirationDate(localDate);

        Mockito.when(cardRepository.findByNumber(number)).thenReturn(null);

        //WHEN
        cardService.processCard(cardVO);

        //THEN
        Mockito.verify(cardRepository, Mockito.times(1)).save(Matchers.any(Card.class));
    }
}
