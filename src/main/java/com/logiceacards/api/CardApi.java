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
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<ResponseDTO> addCard(@RequestBody CardDTO request) {
        ResponseDTO body = cardService.createCard(request);
        return new ResponseEntity<>(body, body.status());
    }

    @GetMapping(value = "/viewById", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<ResponseDTO> findById(@RequestParam Long userId) {
        ResponseDTO body = cardService.viewCard(userId);
        return new ResponseEntity<>(body, body.status());
    }

    @GetMapping(value = "/viewAll", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<ResponseDTO> viewAll() {
        ResponseDTO body = cardService.viewAllCards();
        return new ResponseEntity<>(body, body.status());
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<ResponseDTO> updateCard(@RequestBody CardDTO request) {
        ResponseDTO body = cardService.updateCard(request);
        return new ResponseEntity<>(body, body.status());
    }

    @DeleteMapping(value = "/deleteCard", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<ResponseDTO> deleteCard(@RequestParam Long userId, @RequestParam long cardId) {
        ResponseDTO body = cardService.deleteCard(cardId, userId);
        return new ResponseEntity<>(body, body.status());
    }
}
