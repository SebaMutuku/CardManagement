package com.logiceacards.services;

import com.logiceacards.dto.CardRequestDTO;
import com.logiceacards.dto.ResponseDTO;

public abstract class AbstractCard {
    public abstract ResponseDTO createCard(CardRequestDTO request);

    public abstract ResponseDTO viewCard(CardRequestDTO request) throws Exception;

    public abstract ResponseDTO updateCard(CardRequestDTO request);

    public abstract ResponseDTO deleteCard(long cardId, long userId);

    public abstract ResponseDTO viewAllCards();
}
