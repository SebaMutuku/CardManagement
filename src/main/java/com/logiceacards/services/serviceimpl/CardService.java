package com.logiceacards.services.serviceimpl;


import com.logiceacards.dto.CardDTO;
import com.logiceacards.dto.ResponseDTO;
import com.logiceacards.repos.CardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService extends AbstractCard {
    private final CardRepo cardRepo;

    public CardService(@Autowired CardRepo cardRepo) {
        this.cardRepo = cardRepo;
    }

    @Override
    public ResponseDTO createCard(CardDTO request) {
        return null;
    }

    @Override
    public ResponseDTO viewCard(Long userId) {
        return null;
    }

    @Override
    public ResponseDTO updateCard(CardDTO request,Long cardId) {
        return null;
    }

    @Override
    public ResponseDTO viewAllCards() {
        return null;
    }
}
