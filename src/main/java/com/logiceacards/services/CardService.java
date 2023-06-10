package com.logiceacards.services;


import com.logiceacards.dto.CardDTO;
import com.logiceacards.dto.ResponseDTO;
import com.logiceacards.entities.Card;
import com.logiceacards.entities.User;
import com.logiceacards.repos.CardRepo;
import com.logiceacards.repos.UserRepo;
import com.logiceacards.services.serviceimpl.AbstractCard;
import com.logiceacards.utils.CardStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CardService extends AbstractCard {
    private final CardRepo cardRepo;
    private final UserRepo userRepo;


    @Transactional
    @Override
    public ResponseDTO createCard(CardDTO request) {
        log.info("Create student request ----> [{}]", request);
        return cardRepo.findByCardName(request.cardName()).map(
                card -> new ResponseDTO(card, "Card exists", HttpStatus.ALREADY_REPORTED)
        ).orElseGet(() -> userRepo.findById(request.userId()).map(user -> {
            ResponseDTO dto;
            Card card;
            if (request.cardColor() != null && request.cardColor().startsWith("#") && request.cardColor().length() == 7) {
                card = Card.builder().cardName(request.cardName()).cardColor(request.cardColor())
                        .cardStatus(CardStatus.TODO.name()).userId(user.getUserId()).build();
            } else
                card = Card.builder().cardName(request.cardName()).userId(user.getUserId()).cardStatus(CardStatus.TODO.name()).build();
            cardRepo.save(card);
            dto = new ResponseDTO(card, "Success", HttpStatus.CREATED);
            log.info("Student response ---> [{}]", dto);
            return dto;

        }).orElseGet(() -> new ResponseDTO(null, "User with id " + request.userId() + " doesn't exist", HttpStatus.NOT_FOUND)));
    }

    @Override
    public ResponseDTO viewCard(Long userId) {
        ResponseDTO response = cardRepo.findByUserId(userId).map(
                        card -> new ResponseDTO(card, "Success", HttpStatus.FOUND))
                .orElse((new ResponseDTO(null, "No card exists", HttpStatus.NOT_FOUND)));
        log.info("Validation response ----> [{}]", response);
        return response;
    }

    @Override
    public ResponseDTO updateCard(CardDTO request, Long cardId) {
        return cardRepo.findById(cardId).map(
                card -> {
                    card.setCardName(request.cardName());
                    card.setCardColor(request.cardColor());
                    card.setCardStatus(request.cardName());
                    cardRepo.save(card);
                    return new ResponseDTO(card, "Success", HttpStatus.FOUND);
                }).orElseGet(() -> {
            ResponseDTO dto;
            Card card;
            if (request.cardColor() != null && request.cardColor().startsWith("#") && request.cardColor().length() == 7) {
                card = Card.builder().cardName(request.cardName()).cardColor(request.cardColor()).build();
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
