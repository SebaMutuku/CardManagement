package com.logiceacards.services;


import com.logiceacards.dto.CardDTO;
import com.logiceacards.dto.ResponseDTO;
import com.logiceacards.entities.Card;
import com.logiceacards.repos.CardRepo;
import com.logiceacards.repos.UserRepo;
import com.logiceacards.services.serviceimpl.AbstractCard;
import com.logiceacards.utils.CardStatus;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CardService extends AbstractCard {
    private final CardRepo cardRepo;
    private final UserRepo userRepo;


    @Transactional
    @Override
    public ResponseDTO createCard(CardDTO request) {
        log.info("Create Card request ----> [{}]", request);
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
    public ResponseDTO viewCard(CardDTO request) throws Exception {
        Date creationDate = null;
        if (request.createdOn() != null) {
            creationDate = new SimpleDateFormat().parse(request.createdOn());
        }
        Pageable sortConfigs =
                PageRequest.of(0, 20);
        ResponseDTO response = cardRepo.findByCardNameOrCardStatusOrCreatedOnOrCardColor(request.cardName(),
                        request.cardColor(), creationDate, request.cardStatus()).map(
                        card -> new ResponseDTO(card, "Success", HttpStatus.FOUND))
                .orElse((new ResponseDTO(null, "No card exists", HttpStatus.NOT_FOUND)));
        log.info("Validation response ----> [{}]", response);
        return response;
    }

    @Override
    public ResponseDTO updateCard(CardDTO request) {
        return cardRepo.findById(request.cardId()).map(
                card -> {
                    card.setCardName(request.cardName());
                    card.setCardColor(request.cardColor());
                    card.setCardStatus(request.cardStatus());
                    cardRepo.save(card);
                    return new ResponseDTO(card, "Success", HttpStatus.FOUND);
                }).orElseGet(() -> {
            ResponseDTO dto;
            Card card;
            if (request.cardColor() != null && request.cardColor().startsWith("#") && request.cardColor().length() == 7) {
                card = Card.builder().cardName(request.cardName()).cardColor(request.cardColor()).build();
            } else card = Card.builder().cardName(request.cardName()).build();
            cardRepo.save(card);
            dto = new ResponseDTO(card, "Successfully create a new Card", HttpStatus.OK);
            log.info("Card response response ---> [{}]", dto);
            return dto;
        });
    }

    @Override
    public ResponseDTO deleteCard(Long cardId, Long userId) {
        log.info("Delete--> cardId [{}] UserId ----> [{}]", cardId, userId);
        return cardRepo.findById(cardId).map(
                card -> {
                    ResponseDTO response;
                    if (card.getUserId().equals(userId)) {
                        cardRepo.delete(card);
                        response = new ResponseDTO(null, "Successfully deleted card with id " + cardId, HttpStatus.OK);
                    } else
                        response = new ResponseDTO(null, "You are not the owner of card Id " + cardId, HttpStatus.EXPECTATION_FAILED);
                    log.info("Delete card response [{}]", response);
                    return response;
                }
        ).orElseGet(() -> {
            ResponseDTO response = new ResponseDTO(null, "Card with id " + cardId + " not found", HttpStatus.EXPECTATION_FAILED);
            log.info("Delete card response [{}]", response);
            return response;
        });
    }


    @Override
    public ResponseDTO viewAllCards() {
        Optional<ResponseDTO> responseDTO = cardRepo.findAll().stream().map(card -> new ResponseDTO(card, "Success", HttpStatus.OK)).findFirst();
        log.info("Card response response ---> [{}]", responseDTO);
        return responseDTO.get();
    }
}
