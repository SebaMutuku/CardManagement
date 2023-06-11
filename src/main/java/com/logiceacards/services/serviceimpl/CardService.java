package com.logiceacards.services.serviceimpl;


import com.logiceacards.dto.CardRequestDTO;
import com.logiceacards.dto.ResponseDTO;
import com.logiceacards.entities.Card;
import com.logiceacards.repos.CardRepo;
import com.logiceacards.repos.UserRepo;
import com.logiceacards.services.AbstractCard;
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
    public ResponseDTO createCard(CardRequestDTO request) {
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
    public ResponseDTO viewCard(CardRequestDTO request) throws Exception {
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
    public ResponseDTO updateCard(CardRequestDTO request) {
        log.info("Received Request [{}]", request);
        return cardRepo.findByCardIdAndUserId(request.cardId(), request.userId()).map(
                card -> {
                    card.setCardName(request.cardName());
                    card.setCardColor(request.cardColor());
                    card.setCardStatus(request.cardStatus());
                    cardRepo.save(card);
                    return new ResponseDTO(card, "Successfully updated card", HttpStatus.CREATED);
                }).orElseGet(() -> new ResponseDTO(null, "Card not found", HttpStatus.CREATED));
    }

    @Override
    public ResponseDTO deleteCard(long cardId, long userId) {
        log.info("Delete--> cardId [{}] UserId ----> [{}]", cardId, userId);
        return cardRepo.findByCardIdAndUserId(cardId, userId).map(
                card -> {
                    ResponseDTO response = new ResponseDTO(null, "Successfully deleted card with id " + cardId, HttpStatus.OK);
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
