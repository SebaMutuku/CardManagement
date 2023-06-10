package com.logiceacards.services.serviceimpl;

import com.logiceacards.dto.CardDTO;
import com.logiceacards.dto.ResponseDTO;

public abstract class AbstractCard {
    public abstract ResponseDTO createCard(CardDTO request);

    public abstract ResponseDTO viewCard(Long userId);

    public abstract ResponseDTO updateCard(CardDTO request);
    public abstract ResponseDTO deleteCard(Long cardId,Long userId);

    public abstract ResponseDTO viewAllCards();
}
