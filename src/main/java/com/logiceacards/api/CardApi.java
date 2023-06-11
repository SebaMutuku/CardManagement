package com.logiceacards.api;


import com.logiceacards.dto.CardRequestDTO;
import com.logiceacards.dto.ResponseDTO;
import com.logiceacards.services.serviceimpl.CardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<ResponseDTO> addCard(@RequestBody CardRequestDTO request) {
        ResponseDTO body = cardService.createCard(request);
        return new ResponseEntity<>(body, body.status());
    }

    @GetMapping(value = "/viewById", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<ResponseDTO> findById(@RequestBody CardRequestDTO request) {
        try {
            ResponseDTO body = cardService.viewCard(request);
            return new ResponseEntity<>(body, body.status());
        } catch (Exception exception) {
            return new ResponseEntity<>(new ResponseDTO(null, exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(value = "/viewAll", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<ResponseDTO> viewAll() {
        ResponseDTO body = cardService.viewAllCards();
        return new ResponseEntity<>(body, body.status());
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<ResponseDTO> updateCard(@RequestBody CardRequestDTO request) {
        ResponseDTO body = cardService.updateCard(request);
        return new ResponseEntity<>(body, body.status());
    }

    @DeleteMapping(value = "/deleteCard/{userId}/{cardId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<ResponseDTO> deleteCard(@PathVariable long userId, @PathVariable long cardId) {
        ResponseDTO body = cardService.deleteCard(cardId, userId);
        return new ResponseEntity<>(body, body.status());
    }
}
