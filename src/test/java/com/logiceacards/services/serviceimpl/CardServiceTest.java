package com.logiceacards.services.serviceimpl;

import com.logiceacards.dto.CardRequestDTO;
import com.logiceacards.dto.ResponseDTO;
import com.logiceacards.entities.Card;
import com.logiceacards.entities.User;
import com.logiceacards.repos.CardRepo;
import com.logiceacards.repos.UserRepo;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringJUnitConfig
@SpringBootTest
class CardServiceTest {
    @MockBean
    private CardRepo cardRepo;

    @MockBean
    private UserRepo userRepo;

    @Test
    public void testCreateCardReturnsCardExists() {
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
    public void testCreateCardReturnsPayloadWithSuccess() {
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
        assertNotNull(((Card) payloadResult).getCreatedOn());
        assertEquals("TODO", ((Card) payloadResult).getCardStatus());
        assertEquals("Card Name", ((Card) payloadResult).getCardName());
        assertEquals(0L, ((Card) payloadResult).getCardId());
        verify(cardRepo).save(Mockito.any());
        verify(cardRepo).findByCardName(Mockito.any());
        verify(userRepo).findById(Mockito.<Long>any());
    }


    @Test
    public void testViewCardReturnsPayloadWithSuccess() throws Exception {
        Card card = new Card();
        when(cardRepo.findByUserIdOrCardNameOrCreatedOnOrCardStatusOrCardColorOrderByCardNameAscCardColorAscCardStatusDescCreatedOnDesc(
                anyLong(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any()))
                .thenReturn(List.of((new Card())));
        CardService cardService = new CardService(cardRepo, userRepo);
        ResponseDTO actualViewCardResult = cardService
                .viewCard(new CardRequestDTO("Card Name", "Card Color", 1L, 1L, "Card Status", null), 1);
        assertEquals("Success", actualViewCardResult.message());
        assertEquals(HttpStatus.FOUND, actualViewCardResult.status());
        verify(cardRepo).findByUserIdOrCardNameOrCreatedOnOrCardStatusOrCardColorOrderByCardNameAscCardColorAscCardStatusDescCreatedOnDesc(
                anyLong(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any());
    }

    @Test
    public void testViewCardFailsToFindACard() throws Exception {
        when(cardRepo.findByUserIdOrCardNameOrCreatedOnOrCardStatusOrCardColorOrderByCardNameAscCardColorAscCardStatusDescCreatedOnDesc(
                anyLong(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any()))
                .thenReturn(List.of());
        CardService cardService = new CardService(cardRepo, userRepo);
        ResponseDTO actualViewCardResult = cardService
                .viewCard(new CardRequestDTO("Card Name", "Card Color", 100L, 1L, "Card Status", null), 1);
        assertEquals("No card exists", actualViewCardResult.message());
        assertEquals(HttpStatus.NOT_FOUND, actualViewCardResult.status());
        assertNull(actualViewCardResult.payload());
        verify(cardRepo).findByUserIdOrCardNameOrCreatedOnOrCardStatusOrCardColorOrderByCardNameAscCardColorAscCardStatusDescCreatedOnDesc(
                anyLong(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any());
    }


    @Test
    public void testUpdateCardReturnsPayloadWithSuccess() {
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
    public void testUpdateCardReturnsPayloadWithCardNotFound() {
        when(cardRepo.save(Mockito.any())).thenReturn(new Card());
        when(cardRepo.findByCardIdAndUserId(anyLong(), anyLong())).thenReturn(Optional.empty());
        CardService cardService = new CardService(cardRepo, userRepo);
        ResponseDTO actualUpdateCardResult = cardService.updateCard(
                new CardRequestDTO("Card Name", "Card Color", 1L, 1L, "Card Status", "Jan 1, 2020 8:00am GMT+0100"));
        assertEquals("Card not found", actualUpdateCardResult.message());
        assertEquals(HttpStatus.NOT_FOUND, actualUpdateCardResult.status());
        assertNull(actualUpdateCardResult.payload());
        verify(cardRepo).findByCardIdAndUserId(anyLong(), anyLong());
    }

    @Test
    public void testDeleteCardReturnsPayloadWithSuccess() {
        when(cardRepo.findByCardIdAndUserId(anyLong(), anyLong())).thenReturn(Optional.of(new Card()));
        ResponseDTO actualDeleteCardResult = (new CardService(cardRepo, userRepo)).deleteCard(1L, 1L);
        assertEquals("Successfully deleted card with id 1", actualDeleteCardResult.message());
        assertEquals(HttpStatus.OK, actualDeleteCardResult.status());
        assertNull(actualDeleteCardResult.payload());
        verify(cardRepo).findByCardIdAndUserId(anyLong(), anyLong());
    }

    @Test
    public void testDeleteCardReturnsPayloadWithCardNotFound() {
        when(cardRepo.findByCardIdAndUserId(anyLong(), anyLong())).thenReturn(Optional.empty());
        ResponseDTO actualDeleteCardResult = (new CardService(cardRepo, userRepo)).deleteCard(1L, 1L);
        assertEquals("Card with id 1 not found", actualDeleteCardResult.message());
        assertEquals(HttpStatus.EXPECTATION_FAILED, actualDeleteCardResult.status());
        assertNull(actualDeleteCardResult.payload());
        verify(cardRepo).findByCardIdAndUserId(anyLong(), anyLong());
    }

    @Test
    void testViewAllCards() {
        ArrayList<Card> content = new ArrayList<>();
        content.add(new Card());
        PageImpl<Card> pageImpl = new PageImpl<>(content);
        CardRepo cardRepo = mock(CardRepo.class);
        when(cardRepo.findAll(Mockito.<Pageable>any())).thenReturn(pageImpl);
        ResponseDTO actualViewAllCardsResult = (new CardService(cardRepo, mock(UserRepo.class))).viewAllCards(3);
        assertEquals("Success", actualViewAllCardsResult.message());
        assertEquals(HttpStatus.OK, actualViewAllCardsResult.status());
        Object payloadResult = actualViewAllCardsResult.payload();
        assertSame(pageImpl, payloadResult);
        assertEquals(1, ((PageImpl<Object>) payloadResult).toList().size());
        verify(cardRepo).findAll(Mockito.<Pageable>any());
    }


}

