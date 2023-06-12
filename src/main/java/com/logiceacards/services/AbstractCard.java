package com.logiceacards.services;

import com.logiceacards.dto.CardRequestDTO;
import com.logiceacards.dto.ResponseDTO;
import org.springframework.data.domain.Page;

public abstract class AbstractCard {
    public abstract ResponseDTO createCard(CardRequestDTO request);

    public abstract ResponseDTO viewCard(CardRequestDTO request, int pageSize) throws Exception;

    public abstract ResponseDTO updateCard(CardRequestDTO request);

    public abstract ResponseDTO deleteCard(long cardId, long userId);

    public abstract ResponseDTO viewAllCards(int pageSize);
}
