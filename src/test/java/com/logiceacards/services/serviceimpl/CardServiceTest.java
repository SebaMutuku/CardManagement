package com.logiceacards.services.serviceimpl;

import com.logiceacards.dto.CardRequestDTO;
import com.logiceacards.dto.ResponseDTO;
import com.logiceacards.entities.Card;
import com.logiceacards.entities.User;
import com.logiceacards.repos.CardRepo;
import com.logiceacards.repos.UserRepo;
import java.util.ArrayList;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringJUnitConfig
@SpringBootTest
class CardServiceTest {
    @MockBean
    private CardRepo cardRepo;

    @MockBean
    private UserRepo userRepo;

    @Test
    void testCreateCardReturnsCardExists() {
        Card card = new Card();
        when(cardRepo.findByCardName(Mockito.any())).thenReturn(Optional.of(card));
        CardService cardService = new CardService(cardRepo, userRepo);
        ResponseDTO actualCreateCardResult = cardService.createCard(
                new CardRequestDTO("Card Name", "Card Color", 1L, 1L, "Card Status", "Jan 1, 2020 8:00am GMT+0100"));
        assertEquals("Card exists", actualCreateCardResult.message());
        assertEquals(HttpStatus.ALREADY_REPORTED, actualCreateCardResult.status());
        assertSame(card, actualCreateCardResult.payload());
        verify(cardRepo).findByCardName(Mockito.any());
    }

    @Test
    void testCreateCardReturnsPayloadWithSuccess() {
        when(cardRepo.save(Mockito.any())).thenReturn(new Card());
        when(cardRepo.findByCardName(Mockito.any())).thenReturn(Optional.empty());
        when(userRepo.findById(Mockito.<Long>any())).thenReturn(Optional.of(new User()));
        CardService cardService = new CardService(cardRepo, userRepo);
        ResponseDTO actualCreateCardResult = cardService.createCard(
                new CardRequestDTO("Card Name", "Card Color", 1L, 1L, "Card Status", "Jan 1, 2020 8:00am GMT+0100"));
        assertEquals("Success", actualCreateCardResult.message());
        assertEquals(HttpStatus.CREATED, actualCreateCardResult.status());
        Object payloadResult = actualCreateCardResult.payload();
        assertTrue(payloadResult instanceof Card);
        assertNull(((Card) payloadResult).getCardColor());
        assertEquals(0L, ((Card) payloadResult).getUserId());
        assertNull(((Card) payloadResult).getCreatedOn());
        assertEquals("TODO", ((Card) payloadResult).getCardStatus());
        assertEquals("Card Name", ((Card) payloadResult).getCardName());
        assertEquals(0L, ((Card) payloadResult).getCardId());
        verify(cardRepo).save(Mockito.any());
        verify(cardRepo).findByCardName(Mockito.any());
        verify(userRepo).findById(Mockito.<Long>any());
    }


    @Test
    void testViewCardReturnsPayloadWithSuccess() throws Exception {
        Card card = new Card();
        when(cardRepo.findByUserIdOrCardNameOrCreatedOnOrCardStatusOrCardColorOrderByCardNameAscCardColorAscCardStatusDescCreatedOnDesc(
                anyLong(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any()))
                .thenReturn(Optional.of(card));
        CardService cardService = new CardService(cardRepo, userRepo);
        ResponseDTO actualViewCardResult = cardService
                .viewCard(new CardRequestDTO("Card Name", "Card Color", 1L, 1L, "Card Status", null));
        assertEquals("Success", actualViewCardResult.message());
        assertEquals(HttpStatus.FOUND, actualViewCardResult.status());
        assertSame(card, actualViewCardResult.payload());
        verify(cardRepo).findByUserIdOrCardNameOrCreatedOnOrCardStatusOrCardColorOrderByCardNameAscCardColorAscCardStatusDescCreatedOnDesc(
                anyLong(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any());
    }

    @Test
    void testViewCardFailsToFindACard() throws Exception {
        when(cardRepo.findByUserIdOrCardNameOrCreatedOnOrCardStatusOrCardColorOrderByCardNameAscCardColorAscCardStatusDescCreatedOnDesc(
                anyLong(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any()))
                .thenReturn(Optional.empty());
        CardService cardService = new CardService(cardRepo, userRepo);
        ResponseDTO actualViewCardResult = cardService
                .viewCard(new CardRequestDTO("Card Name", "Card Color", 1L, 1L, "Card Status", null));
        assertEquals("No card exists", actualViewCardResult.message());
        assertEquals(HttpStatus.NOT_FOUND, actualViewCardResult.status());
        assertNull(actualViewCardResult.payload());
        verify(cardRepo).findByUserIdOrCardNameOrCreatedOnOrCardStatusOrCardColorOrderByCardNameAscCardColorAscCardStatusDescCreatedOnDesc(
                anyLong(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any());
    }


    @Test
    void testUpdateCardReturnsPayloadWithSuccess() {
        when(cardRepo.save(Mockito.any())).thenReturn(new Card());
        Card card = new Card();
        when(cardRepo.findByCardIdAndUserId(anyLong(), anyLong())).thenReturn(Optional.of(card));
        CardService cardService = new CardService(cardRepo, userRepo);
        ResponseDTO actualUpdateCardResult = cardService.updateCard(
                new CardRequestDTO("Card Name", "Card Color", 1L, 1L, "Card Status", "Jan 1, 2020 8:00am GMT+0100"));
        assertEquals("Successfully updated card", actualUpdateCardResult.message());
        assertEquals(HttpStatus.CREATED, actualUpdateCardResult.status());
        Object payloadResult = actualUpdateCardResult.payload();
        assertSame(card, payloadResult);
        assertEquals("Card Color", ((Card) payloadResult).getCardColor());
        assertEquals("Card Status", ((Card) payloadResult).getCardStatus());
        assertEquals("Card Name", ((Card) payloadResult).getCardName());
        verify(cardRepo).save(Mockito.any());
        verify(cardRepo).findByCardIdAndUserId(anyLong(), anyLong());
    }

    @Test
    void testUpdateCardReturnsPayloadWithCardNotFound() {
        when(cardRepo.save(Mockito.any())).thenReturn(new Card());
        when(cardRepo.findByCardIdAndUserId(anyLong(), anyLong())).thenReturn(Optional.empty());
        CardService cardService = new CardService(cardRepo, userRepo);
        ResponseDTO actualUpdateCardResult = cardService.updateCard(
                new CardRequestDTO("Card Name", "Card Color", 1L, 1L, "Card Status", "Jan 1, 2020 8:00am GMT+0100"));
        assertEquals("Card not found", actualUpdateCardResult.message());
        assertEquals(HttpStatus.CREATED, actualUpdateCardResult.status());
        assertNull(actualUpdateCardResult.payload());
        verify(cardRepo).findByCardIdAndUserId(anyLong(), anyLong());
    }

    @Test
    void testDeleteCardReturnsPayloadWithSuccess() {
        when(cardRepo.findByCardIdAndUserId(anyLong(), anyLong())).thenReturn(Optional.of(new Card()));
        ResponseDTO actualDeleteCardResult = (new CardService(cardRepo, userRepo)).deleteCard(1L, 1L);
        assertEquals("Successfully deleted card with id 1", actualDeleteCardResult.message());
        assertEquals(HttpStatus.OK, actualDeleteCardResult.status());
        assertNull(actualDeleteCardResult.payload());
        verify(cardRepo).findByCardIdAndUserId(anyLong(), anyLong());
    }

    @Test
    void testDeleteCardReturnsPayloadWithCardNotFound() {
        when(cardRepo.findByCardIdAndUserId(anyLong(), anyLong())).thenReturn(Optional.empty());
        ResponseDTO actualDeleteCardResult = (new CardService(cardRepo, userRepo)).deleteCard(1L, 1L);
        assertEquals("Card with id 1 not found", actualDeleteCardResult.message());
        assertEquals(HttpStatus.EXPECTATION_FAILED, actualDeleteCardResult.status());
        assertNull(actualDeleteCardResult.payload());
        verify(cardRepo).findByCardIdAndUserId(anyLong(), anyLong());
    }


    @Test
    void testViewAllCards() {
        ArrayList<Card> cardList = new ArrayList<>();
        Card card = new Card();
        cardList.add(card);
        when(cardRepo.findAll()).thenReturn(cardList);
        ResponseDTO actualViewAllCardsResult = (new CardService(cardRepo, userRepo)).viewAllCards();
        assertEquals("Success", actualViewAllCardsResult.message());
        assertEquals(HttpStatus.OK, actualViewAllCardsResult.status());
        assertSame(card, actualViewAllCardsResult.payload());
        verify(cardRepo).findAll();
    }
}

