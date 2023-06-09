package com.logiceacards.services.serviceimpl;


import com.logiceacards.dto.CardDTO;
import com.logiceacards.dto.ResponseDTO;
import com.logiceacards.entities.Card;
import com.logiceacards.repos.CardRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class CardService extends AbstractCard {
    private final CardRepo cardRepo;

    public CardService(@Autowired CardRepo cardRepo) {
        this.cardRepo = cardRepo;
    }


    @Transactional
    @Override
    public ResponseDTO createCard(CardDTO request) {
        log.info("Create student request ----> [{}]", request);
        return cardRepo.findByCardName(request.cardName()).map(
                card -> new ResponseDTO(card, "Card exists", HttpStatus.ALREADY_REPORTED)
        ).orElseGet(() -> {
            ResponseDTO dto;
            Card card;
            if (request.cardColor().isPresent() && request.cardColor().get().startsWith("#")) {
                card = Card.builder().cardName(request.cardName()).cardColor(request.cardColor().get()).build();
            } else card = Card.builder().cardName(request.cardName()).build();
            cardRepo.save(card);
            dto = new ResponseDTO(card, "Success", HttpStatus.CREATED);
            log.info("Student response ---> [{}]", dto);
            return dto;

        });
    }

    @Override
    public ResponseDTO viewCard(Long userId) {
        ResponseDTO response = cardRepo.findByUserId(userId).map(
                card -> new ResponseDTO(card, "Success", HttpStatus.FOUND)).orElse((new ResponseDTO(null, "No card exists", HttpStatus.NOT_FOUND)));
        log.info("Validation response ----> [{}]", response);
        return response;
    }

    @Override
    public ResponseDTO updateCard(CardDTO request, Long cardId) {
        return cardRepo.findById(cardId).map(
                card -> {
                    card.setCardName(request.cardName());
                    card.setCardColor(request.cardColor().get());
                    card.setCardStatus(request.cardName());
                    cardRepo.save(card);
                    return new ResponseDTO(card, "Success", HttpStatus.FOUND);
                }).orElseGet(() -> {
            ResponseDTO dto;
            Card card;
            if (request.cardColor().isPresent() && request.cardColor().get().startsWith("#")) {
                card = Card.builder().cardName(request.cardName()).cardColor(request.cardColor().get()).build();
            } else card = Card.builder().cardName(request.cardName()).build();
            cardRepo.save(card);
            dto = new ResponseDTO(card, "Successfully create a new Card", HttpStatus.CREATED);
            log.info("Card response response ---> [{}]", dto);
            return dto;
        });
    }

    @Override
    public ResponseDTO viewAllCards() {
        Optional<ResponseDTO> responseDTO = cardRepo.findAll().stream().map(card -> new ResponseDTO(card, "Success", HttpStatus.OK)).findFirst();
        log.info("Card response response ---> [{}]", responseDTO);
        return responseDTO.get();
    }
}
