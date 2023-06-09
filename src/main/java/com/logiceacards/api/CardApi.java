package com.logiceacards.api;


import com.logiceacards.dto.CardDTO;
import com.logiceacards.dto.ResponseDTO;
import com.logiceacards.services.serviceimpl.CardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/logiceaCard/v1/", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class CardApi {
    private final CardService cardService;

    public CardApi(@Autowired CardService cardService) {
        this.cardService = cardService;
    }


    @PostMapping(value = "/card/createCard", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<ResponseDTO> addCard(@RequestBody CardDTO request) {
        ResponseDTO body = cardService.createCard(request);
        log.info("Create student response --> [{}]", body);
        return new ResponseEntity<>(body, body.status());
    }

    @GetMapping(value = "/card/viewCard", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<ResponseDTO> f(@RequestParam Long userId) {
        ResponseDTO body = cardService.viewCard(userId);
        log.info("Create student response --> [{}]", body);
        return new ResponseEntity<>(body, body.status());
    }

    @GetMapping(value = "/card/viewCard", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseDTO> viewAll() {
        ResponseDTO body = cardService.viewAllCards();
        log.info("Create student response --> [{}]", body);
        return new ResponseEntity<>(body, body.status());
    }

    @PutMapping(value = "/card/updateCard", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<ResponseDTO> updateCard(@RequestBody CardDTO request, @RequestParam Long cardId) {
        ResponseDTO body = cardService.updateCard(request, cardId);
        log.info("Create student response --> [{}]", body);
        return new ResponseEntity<>(body, body.status());
    }
}
