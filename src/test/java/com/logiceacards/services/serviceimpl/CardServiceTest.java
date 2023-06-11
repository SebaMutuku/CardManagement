package com.logiceacards.services.serviceimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.logiceacards.dto.CardRequestDTO;
import com.logiceacards.dto.ResponseDTO;
import com.logiceacards.entities.Card;
import com.logiceacards.entities.User;
import com.logiceacards.repos.CardRepo;
import com.logiceacards.repos.UserRepo;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;


@SpringJUnitConfig
@SpringBootTest
class CardServiceTest {
    /**
     * Method under test: {@link CardService#createCard(CardRequestDTO)}
     */
    @Test
    void testCreateCard() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        CardRepo cardRepo = mock(CardRepo.class);
        Card card = new Card();
        when(cardRepo.findByCardName(Mockito.<String>any())).thenReturn(Optional.of(card));
        CardService cardService = new CardService(cardRepo, mock(UserRepo.class));
        ResponseDTO actualCreateCardResult = cardService.createCard(
                new CardRequestDTO("Card Name", "Card Color", 1L, 1L, "Card Status", "Jan 1, 2020 8:00am GMT+0100"));
        assertEquals("Card exists", actualCreateCardResult.message());
        assertEquals(HttpStatus.ALREADY_REPORTED, actualCreateCardResult.status());
        assertSame(card, actualCreateCardResult.payload());
        verify(cardRepo).findByCardName(Mockito.<String>any());
    }

    /**
     * Method under test: {@link CardService#createCard(CardRequestDTO)}
     */
    @Test
    void testCreateCard2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        CardRepo cardRepo = mock(CardRepo.class);
        when(cardRepo.save(Mockito.<Card>any())).thenReturn(new Card());
        when(cardRepo.findByCardName(Mockito.<String>any())).thenReturn(Optional.empty());
        UserRepo userRepo = mock(UserRepo.class);
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
        verify(cardRepo).save(Mockito.<Card>any());
        verify(cardRepo).findByCardName(Mockito.<String>any());
        verify(userRepo).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link CardService#createCard(CardRequestDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateCard3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.util.Optional.map(java.util.function.Function)" because the return value of "com.logiceacards.repos.CardRepo.findByCardName(String)" is null
        //       at com.logiceacards.services.serviceimpl.CardService.createCard(CardService.java:33)
        //   See https://diff.blue/R013 to resolve this issue.

        CardRepo cardRepo = mock(CardRepo.class);
        when(cardRepo.save(Mockito.<Card>any())).thenReturn(new Card());
        when(cardRepo.findByCardName(Mockito.<String>any())).thenReturn(null);
        UserRepo userRepo = mock(UserRepo.class);
        when(userRepo.findById(Mockito.<Long>any())).thenReturn(Optional.of(new User()));
        CardService cardService = new CardService(cardRepo, userRepo);
        cardService.createCard(
                new CardRequestDTO("Card Name", "Card Color", 1L, 1L, "Card Status", "Jan 1, 2020 8:00am GMT+0100"));
    }

    /**
     * Method under test: {@link CardService#createCard(CardRequestDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateCard4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.util.Optional.map(java.util.function.Function)" because the return value of "com.logiceacards.repos.UserRepo.findById(Object)" is null
        //       at com.logiceacards.services.serviceimpl.CardService.lambda$createCard$3(CardService.java:35)
        //       at java.util.Optional.orElseGet(Optional.java:364)
        //       at com.logiceacards.services.serviceimpl.CardService.createCard(CardService.java:35)
        //   See https://diff.blue/R013 to resolve this issue.

        CardRepo cardRepo = mock(CardRepo.class);
        when(cardRepo.save(Mockito.<Card>any())).thenReturn(new Card());
        when(cardRepo.findByCardName(Mockito.<String>any())).thenReturn(Optional.empty());
        UserRepo userRepo = mock(UserRepo.class);
        when(userRepo.findById(Mockito.<Long>any())).thenReturn(null);
        CardService cardService = new CardService(cardRepo, userRepo);
        cardService.createCard(
                new CardRequestDTO("Card Name", "Card Color", 1L, 1L, "Card Status", "Jan 1, 2020 8:00am GMT+0100"));
    }

    /**
     * Method under test: {@link CardService#createCard(CardRequestDTO)}
     */
    @Test
    void testCreateCard5() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        CardRepo cardRepo = mock(CardRepo.class);
        when(cardRepo.save(Mockito.<Card>any())).thenReturn(new Card());
        when(cardRepo.findByCardName(Mockito.<String>any())).thenReturn(Optional.empty());
        UserRepo userRepo = mock(UserRepo.class);
        when(userRepo.findById(Mockito.<Long>any())).thenReturn(Optional.empty());
        CardService cardService = new CardService(cardRepo, userRepo);
        ResponseDTO actualCreateCardResult = cardService.createCard(
                new CardRequestDTO("Card Name", "Card Color", 1L, 1L, "Card Status", "Jan 1, 2020 8:00am GMT+0100"));
        assertEquals("User with id 1 doesn't exist", actualCreateCardResult.message());
        assertEquals(HttpStatus.NOT_FOUND, actualCreateCardResult.status());
        assertNull(actualCreateCardResult.payload());
        verify(cardRepo).findByCardName(Mockito.<String>any());
        verify(userRepo).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link CardService#createCard(CardRequestDTO)}
     */
    @Test
    void testCreateCard6() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        CardRepo cardRepo = mock(CardRepo.class);
        when(cardRepo.save(Mockito.<Card>any())).thenReturn(new Card());
        when(cardRepo.findByCardName(Mockito.<String>any())).thenReturn(Optional.empty());
        UserRepo userRepo = mock(UserRepo.class);
        when(userRepo.findById(Mockito.<Long>any())).thenReturn(Optional.of(new User()));
        CardService cardService = new CardService(cardRepo, userRepo);
        ResponseDTO actualCreateCardResult = cardService
                .createCard(new CardRequestDTO("Card Name", "#", 1L, 1L, "Card Status", "Jan 1, 2020 8:00am GMT+0100"));
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
        verify(cardRepo).save(Mockito.<Card>any());
        verify(cardRepo).findByCardName(Mockito.<String>any());
        verify(userRepo).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link CardService#createCard(CardRequestDTO)}
     */
    @Test
    void testCreateCard7() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        CardRepo cardRepo = mock(CardRepo.class);
        when(cardRepo.save(Mockito.<Card>any())).thenReturn(new Card());
        when(cardRepo.findByCardName(Mockito.<String>any())).thenReturn(Optional.empty());
        UserRepo userRepo = mock(UserRepo.class);
        when(userRepo.findById(Mockito.<Long>any())).thenReturn(Optional.of(new User()));
        CardService cardService = new CardService(cardRepo, userRepo);
        ResponseDTO actualCreateCardResult = cardService
                .createCard(new CardRequestDTO("Card Name", null, 1L, 1L, "Card Status", "Jan 1, 2020 8:00am GMT+0100"));
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
        verify(cardRepo).save(Mockito.<Card>any());
        verify(cardRepo).findByCardName(Mockito.<String>any());
        verify(userRepo).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link CardService#createCard(CardRequestDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateCard8() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.logiceacards.dto.CardRequestDTO.cardName()" because "request" is null
        //       at com.logiceacards.services.serviceimpl.CardService.createCard(CardService.java:33)
        //   See https://diff.blue/R013 to resolve this issue.

        CardRepo cardRepo = mock(CardRepo.class);
        when(cardRepo.save(Mockito.<Card>any())).thenReturn(new Card());
        when(cardRepo.findByCardName(Mockito.<String>any())).thenReturn(Optional.empty());
        UserRepo userRepo = mock(UserRepo.class);
        when(userRepo.findById(Mockito.<Long>any())).thenReturn(Optional.of(new User()));
        (new CardService(cardRepo, userRepo)).createCard(null);
    }

    /**
     * Method under test: {@link CardService#viewCard(CardRequestDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testViewCard() throws Exception {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.text.ParseException: Unparseable date: "Jan 1, 2020 8:00am GMT+0100"
        //       at java.text.DateFormat.parse(DateFormat.java:399)
        //       at com.logiceacards.services.serviceimpl.CardService.viewCard(CardService.java:55)
        //   See https://diff.blue/R013 to resolve this issue.

        CardService cardService = new CardService(mock(CardRepo.class), mock(UserRepo.class));
        cardService.viewCard(
                new CardRequestDTO("Card Name", "Card Color", 1L, 1L, "Card Status", "Jan 1, 2020 8:00am GMT+0100"));
    }

    /**
     * Method under test: {@link CardService#viewCard(CardRequestDTO)}
     */
    @Test
    void testViewCard2() throws Exception {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        CardRepo cardRepo = mock(CardRepo.class);
        Card card = new Card();
        when(cardRepo
                .findByUserIdOrCardNameOrCreatedOnOrCardStatusOrCardColorOrderByCardNameAscCardColorAscCardStatusDescCreatedOnDesc(
                        anyLong(), Mockito.<String>any(), Mockito.<Date>any(), Mockito.<String>any(), Mockito.<String>any()))
                .thenReturn(Optional.of(card));
        CardService cardService = new CardService(cardRepo, mock(UserRepo.class));
        ResponseDTO actualViewCardResult = cardService
                .viewCard(new CardRequestDTO("Card Name", "Card Color", 1L, 1L, "Card Status", null));
        assertEquals("Success", actualViewCardResult.message());
        assertEquals(HttpStatus.FOUND, actualViewCardResult.status());
        assertSame(card, actualViewCardResult.payload());
        verify(cardRepo)
                .findByUserIdOrCardNameOrCreatedOnOrCardStatusOrCardColorOrderByCardNameAscCardColorAscCardStatusDescCreatedOnDesc(
                        anyLong(), Mockito.<String>any(), Mockito.<Date>any(), Mockito.<String>any(), Mockito.<String>any());
    }

    /**
     * Method under test: {@link CardService#viewCard(CardRequestDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testViewCard3() throws Exception {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.util.Optional.map(java.util.function.Function)" because the return value of "com.logiceacards.repos.CardRepo.findByUserIdOrCardNameOrCreatedOnOrCardStatusOrCardColorOrderByCardNameAscCardColorAscCardStatusDescCreatedOnDesc(long, String, java.util.Date, String, String)" is null
        //       at com.logiceacards.services.serviceimpl.CardService.viewCard(CardService.java:60)
        //   See https://diff.blue/R013 to resolve this issue.

        CardRepo cardRepo = mock(CardRepo.class);
        when(cardRepo
                .findByUserIdOrCardNameOrCreatedOnOrCardStatusOrCardColorOrderByCardNameAscCardColorAscCardStatusDescCreatedOnDesc(
                        anyLong(), Mockito.<String>any(), Mockito.<Date>any(), Mockito.<String>any(), Mockito.<String>any()))
                .thenReturn(null);
        CardService cardService = new CardService(cardRepo, mock(UserRepo.class));
        cardService.viewCard(new CardRequestDTO("Card Name", "Card Color", 1L, 1L, "Card Status", null));
    }

    /**
     * Method under test: {@link CardService#viewCard(CardRequestDTO)}
     */
    @Test
    void testViewCard4() throws Exception {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        CardRepo cardRepo = mock(CardRepo.class);
        when(cardRepo
                .findByUserIdOrCardNameOrCreatedOnOrCardStatusOrCardColorOrderByCardNameAscCardColorAscCardStatusDescCreatedOnDesc(
                        anyLong(), Mockito.<String>any(), Mockito.<Date>any(), Mockito.<String>any(), Mockito.<String>any()))
                .thenReturn(Optional.empty());
        CardService cardService = new CardService(cardRepo, mock(UserRepo.class));
        ResponseDTO actualViewCardResult = cardService
                .viewCard(new CardRequestDTO("Card Name", "Card Color", 1L, 1L, "Card Status", null));
        assertEquals("No card exists", actualViewCardResult.message());
        assertEquals(HttpStatus.NOT_FOUND, actualViewCardResult.status());
        assertNull(actualViewCardResult.payload());
        verify(cardRepo)
                .findByUserIdOrCardNameOrCreatedOnOrCardStatusOrCardColorOrderByCardNameAscCardColorAscCardStatusDescCreatedOnDesc(
                        anyLong(), Mockito.<String>any(), Mockito.<Date>any(), Mockito.<String>any(), Mockito.<String>any());
    }

    /**
     * Method under test: {@link CardService#viewCard(CardRequestDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testViewCard5() throws Exception {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.logiceacards.dto.CardRequestDTO.createdOn()" because "request" is null
        //       at com.logiceacards.services.serviceimpl.CardService.viewCard(CardService.java:54)
        //   See https://diff.blue/R013 to resolve this issue.

        CardRepo cardRepo = mock(CardRepo.class);
        when(cardRepo
                .findByUserIdOrCardNameOrCreatedOnOrCardStatusOrCardColorOrderByCardNameAscCardColorAscCardStatusDescCreatedOnDesc(
                        anyLong(), Mockito.<String>any(), Mockito.<Date>any(), Mockito.<String>any(), Mockito.<String>any()))
                .thenReturn(Optional.of(new Card()));
        (new CardService(cardRepo, mock(UserRepo.class))).viewCard(null);
    }

    /**
     * Method under test: {@link CardService#updateCard(CardRequestDTO)}
     */
    @Test
    void testUpdateCard() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        CardRepo cardRepo = mock(CardRepo.class);
        when(cardRepo.save(Mockito.<Card>any())).thenReturn(new Card());
        Card card = new Card();
        when(cardRepo.findByCardIdAndUserId(anyLong(), anyLong())).thenReturn(Optional.of(card));
        CardService cardService = new CardService(cardRepo, mock(UserRepo.class));
        ResponseDTO actualUpdateCardResult = cardService.updateCard(
                new CardRequestDTO("Card Name", "Card Color", 1L, 1L, "Card Status", "Jan 1, 2020 8:00am GMT+0100"));
        assertEquals("Successfully updated card", actualUpdateCardResult.message());
        assertEquals(HttpStatus.CREATED, actualUpdateCardResult.status());
        Object payloadResult = actualUpdateCardResult.payload();
        assertSame(card, payloadResult);
        assertEquals("Card Color", ((Card) payloadResult).getCardColor());
        assertEquals("Card Status", ((Card) payloadResult).getCardStatus());
        assertEquals("Card Name", ((Card) payloadResult).getCardName());
        verify(cardRepo).save(Mockito.<Card>any());
        verify(cardRepo).findByCardIdAndUserId(anyLong(), anyLong());
    }

    /**
     * Method under test: {@link CardService#updateCard(CardRequestDTO)}
     */
    @Test
    void testUpdateCard2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        Card card = mock(Card.class);
        doNothing().when(card).setCardColor(Mockito.<String>any());
        doNothing().when(card).setCardName(Mockito.<String>any());
        doNothing().when(card).setCardStatus(Mockito.<String>any());
        Optional<Card> ofResult = Optional.of(card);
        CardRepo cardRepo = mock(CardRepo.class);
        when(cardRepo.save(Mockito.<Card>any())).thenReturn(new Card());
        when(cardRepo.findByCardIdAndUserId(anyLong(), anyLong())).thenReturn(ofResult);
        CardService cardService = new CardService(cardRepo, mock(UserRepo.class));
        ResponseDTO actualUpdateCardResult = cardService.updateCard(
                new CardRequestDTO("Card Name", "Card Color", 1L, 1L, "Card Status", "Jan 1, 2020 8:00am GMT+0100"));
        assertEquals("Successfully updated card", actualUpdateCardResult.message());
        assertEquals(HttpStatus.CREATED, actualUpdateCardResult.status());
        verify(cardRepo).save(Mockito.<Card>any());
        verify(cardRepo).findByCardIdAndUserId(anyLong(), anyLong());
        verify(card).setCardColor(Mockito.<String>any());
        verify(card).setCardName(Mockito.<String>any());
        verify(card).setCardStatus(Mockito.<String>any());
    }

    /**
     * Method under test: {@link CardService#updateCard(CardRequestDTO)}
     */
    @Test
    void testUpdateCard3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        CardRepo cardRepo = mock(CardRepo.class);
        when(cardRepo.save(Mockito.<Card>any())).thenReturn(new Card());
        when(cardRepo.findByCardIdAndUserId(anyLong(), anyLong())).thenReturn(Optional.empty());
        CardService cardService = new CardService(cardRepo, mock(UserRepo.class));
        ResponseDTO actualUpdateCardResult = cardService.updateCard(
                new CardRequestDTO("Card Name", "Card Color", 1L, 1L, "Card Status", "Jan 1, 2020 8:00am GMT+0100"));
        assertEquals("Card not found", actualUpdateCardResult.message());
        assertEquals(HttpStatus.CREATED, actualUpdateCardResult.status());
        assertNull(actualUpdateCardResult.payload());
        verify(cardRepo).findByCardIdAndUserId(anyLong(), anyLong());
    }

    /**
     * Method under test: {@link CardService#updateCard(CardRequestDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateCard4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.logiceacards.dto.CardRequestDTO.cardId()" because "request" is null
        //       at com.logiceacards.services.serviceimpl.CardService.updateCard(CardService.java:70)
        //   See https://diff.blue/R013 to resolve this issue.

        Card card = mock(Card.class);
        doNothing().when(card).setCardColor(Mockito.<String>any());
        doNothing().when(card).setCardName(Mockito.<String>any());
        doNothing().when(card).setCardStatus(Mockito.<String>any());
        Optional<Card> ofResult = Optional.of(card);
        CardRepo cardRepo = mock(CardRepo.class);
        when(cardRepo.save(Mockito.<Card>any())).thenReturn(new Card());
        when(cardRepo.findByCardIdAndUserId(anyLong(), anyLong())).thenReturn(ofResult);
        (new CardService(cardRepo, mock(UserRepo.class))).updateCard(null);
    }

    /**
     * Method under test: {@link CardService#deleteCard(long, long)}
     */
    @Test
    void testDeleteCard() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        CardRepo cardRepo = mock(CardRepo.class);
        when(cardRepo.findByCardIdAndUserId(anyLong(), anyLong())).thenReturn(Optional.of(new Card()));
        ResponseDTO actualDeleteCardResult = (new CardService(cardRepo, mock(UserRepo.class))).deleteCard(1L, 1L);
        assertEquals("Successfully deleted card with id 1", actualDeleteCardResult.message());
        assertEquals(HttpStatus.OK, actualDeleteCardResult.status());
        assertNull(actualDeleteCardResult.payload());
        verify(cardRepo).findByCardIdAndUserId(anyLong(), anyLong());
    }

    /**
     * Method under test: {@link CardService#deleteCard(long, long)}
     */
    @Test
    void testDeleteCard2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        CardRepo cardRepo = mock(CardRepo.class);
        when(cardRepo.findByCardIdAndUserId(anyLong(), anyLong())).thenReturn(Optional.empty());
        ResponseDTO actualDeleteCardResult = (new CardService(cardRepo, mock(UserRepo.class))).deleteCard(1L, 1L);
        assertEquals("Card with id 1 not found", actualDeleteCardResult.message());
        assertEquals(HttpStatus.EXPECTATION_FAILED, actualDeleteCardResult.status());
        assertNull(actualDeleteCardResult.payload());
        verify(cardRepo).findByCardIdAndUserId(anyLong(), anyLong());
    }

    /**
     * Method under test: {@link CardService#viewAllCards()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testViewAllCards() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.get(Optional.java:143)
        //       at com.logiceacards.services.serviceimpl.CardService.viewAllCards(CardService.java:101)
        //   See https://diff.blue/R013 to resolve this issue.

        CardRepo cardRepo = mock(CardRepo.class);
        when(cardRepo.findAll()).thenReturn(new ArrayList<>());
        (new CardService(cardRepo, mock(UserRepo.class))).viewAllCards();
    }

    /**
     * Method under test: {@link CardService#viewAllCards()}
     */
    @Test
    void testViewAllCards2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        ArrayList<Card> cardList = new ArrayList<>();
        Card card = new Card();
        cardList.add(card);
        CardRepo cardRepo = mock(CardRepo.class);
        when(cardRepo.findAll()).thenReturn(cardList);
        ResponseDTO actualViewAllCardsResult = (new CardService(cardRepo, mock(UserRepo.class))).viewAllCards();
        assertEquals("Success", actualViewAllCardsResult.message());
        assertEquals(HttpStatus.OK, actualViewAllCardsResult.status());
        assertSame(card, actualViewAllCardsResult.payload());
        verify(cardRepo).findAll();
    }
}

