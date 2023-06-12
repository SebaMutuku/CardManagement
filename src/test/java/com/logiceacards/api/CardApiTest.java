package com.logiceacards.api;

import com.logiceacards.dto.CardRequestDTO;
import com.logiceacards.dto.ResponseDTO;
import com.logiceacards.entities.Card;
import com.logiceacards.entities.User;
import com.logiceacards.repos.CardRepo;
import com.logiceacards.repos.UserRepo;
import com.logiceacards.services.serviceimpl.CardService;
import java.util.ArrayList;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringJUnitConfig
@SpringBootTest
class CardApiTest {
    @MockBean
    private CardRepo cardRepo;

    @MockBean
    private UserRepo userRepo;

    @Test
    public void testAddCardIfExists() {
        Card card = new Card();
        when(cardRepo.findByCardName(Mockito.any())).thenReturn(Optional.of(card));
        CardApi cardApi = new CardApi(new CardService(cardRepo, userRepo));
        ResponseEntity<ResponseDTO> actualAddCardResult = cardApi
                .addCard(new CardRequestDTO("Card Name", "Card Color", 1L, 1L, "Card Status", "Jan 1, 2020 8:00am GMT+0100"));
        assertTrue(actualAddCardResult.hasBody());
        assertTrue(actualAddCardResult.getHeaders().isEmpty());
        assertEquals(208, actualAddCardResult.getStatusCode().value());
        ResponseDTO body = actualAddCardResult.getBody();
        assertSame(card, body.payload());
        assertEquals("Card exists", body.message());
        assertEquals(HttpStatus.ALREADY_REPORTED, body.status());
        verify(cardRepo).findByCardName(Mockito.any());
    }


    @Test
    void testAddCardDoesntExists() {
        when(cardRepo.save(Mockito.any())).thenReturn(new Card());
        when(cardRepo.findByCardName(Mockito.any())).thenReturn(Optional.empty());
        when(userRepo.findById(Mockito.<Long>any())).thenReturn(Optional.of(new User()));
        CardApi cardApi = new CardApi(new CardService(cardRepo, userRepo));
        ResponseEntity<ResponseDTO> actualAddCardResult = cardApi
                .addCard(new CardRequestDTO("Card Name", "Card Color", 1L, 1L, "Card Status", "Jan 1, 2020 8:00am GMT+0100"));
        assertTrue(actualAddCardResult.hasBody());
        assertTrue(actualAddCardResult.getHeaders().isEmpty());
        assertEquals(201, actualAddCardResult.getStatusCode().value());
        ResponseDTO body = actualAddCardResult.getBody();
        Object payloadResult = body.payload();
        assertTrue(payloadResult instanceof Card);
        assertEquals("Success", body.message());
        assertEquals(HttpStatus.CREATED, body.status());
        assertEquals(0L, ((Card) payloadResult).getUserId());
        assertNull(((Card) payloadResult).getCreatedOn());
        assertEquals("TODO", ((Card) payloadResult).getCardStatus());
        assertEquals("Card Name", ((Card) payloadResult).getCardName());
        assertEquals(0L, ((Card) payloadResult).getCardId());
        assertNull(((Card) payloadResult).getCardColor());
        verify(cardRepo).save(Mockito.any());
        verify(cardRepo).findByCardName(Mockito.any());
        verify(userRepo).findById(Mockito.<Long>any());
    }


    @Test
    void testAddCardWithNonExistingUserId() {
        when(cardRepo.save(Mockito.any())).thenReturn(new Card());
        when(cardRepo.findByCardName(Mockito.any())).thenReturn(Optional.empty());
        when(userRepo.findById(Mockito.<Long>any())).thenReturn(Optional.empty());
        CardApi cardApi = new CardApi(new CardService(cardRepo, userRepo));
        ResponseEntity<ResponseDTO> actualAddCardResult = cardApi
                .addCard(new CardRequestDTO("Card Name", "Card Color", 1L, 1L, "Card Status", "Jan 1, 2020 8:00am GMT+0100"));
        assertTrue(actualAddCardResult.hasBody());
        assertTrue(actualAddCardResult.getHeaders().isEmpty());
        assertEquals(404, actualAddCardResult.getStatusCode().value());
        ResponseDTO body = actualAddCardResult.getBody();
        assertNull(body.payload());
        assertEquals("User with id 1 doesn't exist", body.message());
        assertEquals(HttpStatus.NOT_FOUND, body.status());
        verify(cardRepo).findByCardName(Mockito.any());
        verify(userRepo).findById(Mockito.<Long>any());
    }


    @Test
    void testAddCardWithWrongCardCreator() {
        CardService cardService = mock(CardService.class);
        when(cardService.createCard(Mockito.any()))
                .thenReturn(new ResponseDTO("Payload", "Not all who wander are lost", HttpStatus.CONTINUE));
        CardApi cardApi = new CardApi(cardService);
        ResponseEntity<ResponseDTO> actualAddCardResult = cardApi
                .addCard(new CardRequestDTO("Card Name", "Card Color", 1L, 1L, "Card Status", "Jan 1, 2020 8:00am GMT+0100"));
        assertTrue(actualAddCardResult.hasBody());
        assertTrue(actualAddCardResult.getHeaders().isEmpty());
        assertEquals(100, actualAddCardResult.getStatusCode().value());
        verify(cardService).createCard(Mockito.any());
    }

//    @Test
//    void testFindById() {
//        CardApi cardApi = new CardApi(new CardService(cardRepo, userRepo));
//        ResponseEntity<ResponseDTO> actualFindByIdResult = cardApi.findById(
//                new CardRequestDTO("Card Name", "Card Color", 1L, 1L, "Card Status", "Jan 1, 2020 8:00am GMT+0100"));
//        assertTrue(actualFindByIdResult.hasBody());
//        assertTrue(actualFindByIdResult.getHeaders().isEmpty());
//        assertEquals(500, actualFindByIdResult.getStatusCode().value());
//        ResponseDTO body = actualFindByIdResult.getBody();
//        assertNull(body.payload());
//        assertEquals("Unparseable date: \"Jan 1, 2020 8:00am GMT+0100\"", body.message());
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, body.status());
//    }

    @Test
    void testFindByIdReturnsError() {

        CardApi cardApi = new CardApi(null);
        ResponseEntity<ResponseDTO> actualFindByIdResult = cardApi.findById(
                new CardRequestDTO("Card Name", "Card Color", 1L, 1L, "Card Status", "Jan 1, 2020 8:00am GMT+0100"));
        assertTrue(actualFindByIdResult.hasBody());
        assertTrue(actualFindByIdResult.getHeaders().isEmpty());
        assertEquals(500, actualFindByIdResult.getStatusCode().value());
        ResponseDTO body = actualFindByIdResult.getBody();
        assertNull(body.payload());
        assertEquals("Cannot invoke \"com.logiceacards.services.serviceimpl.CardService.viewCard(com.logiceacards.dto"
                + ".CardRequestDTO)\" because \"this.cardService\" is null", body.message());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, body.status());
    }


    @Test
    void testFindByIdReturnsNull() throws Exception {
        CardService cardService = mock(CardService.class);
        when(cardService.viewCard(Mockito.any(),Mockito.anyInt())).thenReturn(null);
        CardApi cardApi = new CardApi(cardService);
        ResponseEntity<ResponseDTO> actualFindByIdResult = cardApi.findById(
                new CardRequestDTO("Card Name", "Card Color", 1L, 1L, "Card Status", "Jan 1, 2020 8:00am GMT+0100"));
        assertTrue(actualFindByIdResult.hasBody());
        assertTrue(actualFindByIdResult.getHeaders().isEmpty());
        assertEquals(500, actualFindByIdResult.getStatusCode().value());
        ResponseDTO body = actualFindByIdResult.getBody();
        assertNull(body.payload());
        assertEquals("Cannot invoke \"com.logiceacards.dto.ResponseDTO.status()\" because \"body\" is null",
                body.message());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, body.status());
        verify(cardService).viewCard(Mockito.any(),Mockito.anyInt());
    }


    @Test
    void testViewAllReturnsResponse() {
        ArrayList<Card> cardList = new ArrayList<>();
        Card card = new Card();
        cardList.add(card);
        when(cardRepo.findAll()).thenReturn(cardList);
        ResponseEntity<ResponseDTO> actualViewAllResult = (new CardApi(new CardService(cardRepo, userRepo)))
                .viewAll(Mockito.anyInt());
        assertTrue(actualViewAllResult.hasBody());
        assertTrue(actualViewAllResult.getHeaders().isEmpty());
        assertEquals(200, actualViewAllResult.getStatusCode().value());
        ResponseDTO body = actualViewAllResult.getBody();
        assertSame(card, body.payload());
        assertEquals("Success", body.message());
        assertEquals(HttpStatus.OK, body.status());
        verify(cardRepo).findAll();
    }

    @Test
    void testUpdateCard() {
        when(cardRepo.save(Mockito.any())).thenReturn(new Card());
        Card card = new Card();
        when(cardRepo.findByCardIdAndUserId(anyLong(), anyLong())).thenReturn(Optional.of(card));
        CardApi cardApi = new CardApi(new CardService(cardRepo, userRepo));
        ResponseEntity<ResponseDTO> actualUpdateCardResult = cardApi.updateCard(
                new CardRequestDTO("Card Name", "Card Color", 1L, 1L, "Card Status", "Jan 1, 2020 8:00am GMT+0100"));
        assertTrue(actualUpdateCardResult.hasBody());
        assertTrue(actualUpdateCardResult.getHeaders().isEmpty());
        assertEquals(201, actualUpdateCardResult.getStatusCode().value());
        ResponseDTO body = actualUpdateCardResult.getBody();
        Object payloadResult = body.payload();
        assertSame(card, payloadResult);
        assertEquals("Successfully updated card", body.message());
        assertEquals(HttpStatus.CREATED, body.status());
        assertEquals("Card Status", ((Card) payloadResult).getCardStatus());
        assertEquals("Card Name", ((Card) payloadResult).getCardName());
        assertEquals("Card Color", ((Card) payloadResult).getCardColor());
        verify(cardRepo).save(Mockito.any());
        verify(cardRepo).findByCardIdAndUserId(anyLong(), anyLong());
    }


    @Test
    void testUpdateCardFailsIfUserIsWrong() {
        CardService cardService = mock(CardService.class);
        when(cardService.updateCard(Mockito.any()))
                .thenReturn(new ResponseDTO("Payload", "Not all who wander are lost", HttpStatus.CONTINUE));
        CardApi cardApi = new CardApi(cardService);
        ResponseEntity<ResponseDTO> actualUpdateCardResult = cardApi.updateCard(
                new CardRequestDTO("Card Name", "Card Color", 1L, 1L, "Card Status", "Jan 1, 2020 8:00am GMT+0100"));
        assertTrue(actualUpdateCardResult.hasBody());
        assertTrue(actualUpdateCardResult.getHeaders().isEmpty());
        assertEquals(100, actualUpdateCardResult.getStatusCode().value());
        verify(cardService).updateCard(Mockito.any());
    }


    @Test
    void testDeleteCardDeletesCard() {
        when(cardRepo.findByCardIdAndUserId(anyLong(), anyLong())).thenReturn(Optional.of(new Card()));
        ResponseEntity<ResponseDTO> actualDeleteCardResult = (new CardApi(
                new CardService(cardRepo, userRepo))).deleteCard(1L, 1L);
        assertTrue(actualDeleteCardResult.hasBody());
        assertTrue(actualDeleteCardResult.getHeaders().isEmpty());
        assertEquals(200, actualDeleteCardResult.getStatusCode().value());
        ResponseDTO body = actualDeleteCardResult.getBody();
        assertNull(body.payload());
        assertEquals("Successfully deleted card with id 1", body.message());
        assertEquals(HttpStatus.OK, body.status());
        verify(cardRepo).findByCardIdAndUserId(anyLong(), anyLong());
    }

    @Test
    void testDeleteCardFailsWithCardNotFound() {
        when(cardRepo.findByCardIdAndUserId(anyLong(), anyLong())).thenReturn(Optional.empty());
        ResponseEntity<ResponseDTO> actualDeleteCardResult = (new CardApi(
                new CardService(cardRepo, userRepo))).deleteCard(1L, 1L);
        assertTrue(actualDeleteCardResult.hasBody());
        assertTrue(actualDeleteCardResult.getHeaders().isEmpty());
        assertEquals(417, actualDeleteCardResult.getStatusCode().value());
        ResponseDTO body = actualDeleteCardResult.getBody();
        assertNull(body.payload());
        assertEquals("Card with id 1 not found", body.message());
        assertEquals(HttpStatus.EXPECTATION_FAILED, body.status());
        verify(cardRepo).findByCardIdAndUserId(anyLong(), anyLong());
    }

}

