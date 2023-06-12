package com.logiceacards.services;

import com.logiceacards.dto.CardRequestDTO;
import com.logiceacards.dto.ResponseDTO;

public abstract class AbstractCard {
    public abstract ResponseDTO createCard(CardRequestDTO request);

    public abstract ResponseDTO viewCard(CardRequestDTO request, int pageSize) throws Exception;
    public abstract ResponseDTO findByUserId(long userId, long cardId);

    public abstract ResponseDTO updateByUserId(CardRequestDTO request);

    public abstract ResponseDTO deleteByUserId(long cardId, long userId);

    public abstract ResponseDTO viewAllCards(int pageSize);
}
