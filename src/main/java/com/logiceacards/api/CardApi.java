package com.logiceacards.api;


import com.logiceacards.dto.CardDTO;
import com.logiceacards.dto.ResponseDTO;
import com.logiceacards.services.CardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/logicea/v1/card", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@RequiredArgsConstructor
public class CardApi {
    private final CardService cardService;


    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<ResponseDTO> addCard(@RequestBody CardDTO request) {
        ResponseDTO body = cardService.createCard(request);
        log.info("Create student response --> [{}]", body);
        return new ResponseEntity<>(body, body.status());
    }

    @GetMapping(value = "/viewById", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<ResponseDTO> findById(@RequestParam Long userId) {
        ResponseDTO body = cardService.viewCard(userId);
        log.info("Create student response --> [{}]", body);
        return new ResponseEntity<>(body, body.status());
    }

    @GetMapping(value = "/card/viewAll", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseDTO> viewAll() {
        ResponseDTO body = cardService.viewAllCards();
        log.info("Create student response --> [{}]", body);
        return new ResponseEntity<>(body, body.status());
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<ResponseDTO> updateCard(@RequestBody CardDTO request, @RequestParam Long cardId) {
        ResponseDTO body = cardService.updateCard(request, cardId);
        log.info("Create student response --> [{}]", body);
        return new ResponseEntity<>(body, body.status());
    }
}
