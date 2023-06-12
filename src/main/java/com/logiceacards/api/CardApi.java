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

    @GetMapping(value = "/viewById/{pageSize}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<ResponseDTO> findById(@RequestBody CardRequestDTO request, @PathVariable int pageSize) {
        try {
            ResponseDTO body = cardService.viewCard(request, pageSize);
            return new ResponseEntity<>(body, body.status());
        } catch (Exception exception) {
            return new ResponseEntity<>(new ResponseDTO(null, exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(value = "/viewAll/{pageSize}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<ResponseDTO> viewAll(@PathVariable int pageSize) {
        ResponseDTO body = cardService.viewAllCards(pageSize);
        return new ResponseEntity<>(body, body.status());
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<ResponseDTO> updateByUserId(@RequestBody CardRequestDTO request) {
        ResponseDTO body = cardService.updateByUserId(request);
        return new ResponseEntity<>(body, body.status());
    }

    @DeleteMapping(value = "/deleteCard/{userId}/{cardId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<ResponseDTO> deleteByUserId(@PathVariable long userId, @PathVariable long cardId) {
        ResponseDTO body = cardService.deleteByUserId(cardId, userId);
        return new ResponseEntity<>(body, body.status());
    }

    @GetMapping(value = "/getSingleCard/{userId}/{cardId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<ResponseDTO> getByUserId(@PathVariable long userId, @PathVariable long cardId) {
        ResponseDTO body = cardService.findByUserId(userId, cardId);
        return new ResponseEntity<>(body, body.status());
    }
}
