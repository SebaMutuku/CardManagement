package com.logiceacards.api;


import com.logiceacards.dto.CardRequestDTO;
import com.logiceacards.dto.ResponseDTO;
import com.logiceacards.services.serviceimpl.CardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Page<ResponseDTO>> viewAll(@PathVariable int pageSize) {
        Page<ResponseDTO> body = cardService.viewAllCards(pageSize);
        return new ResponseEntity<>(body, HttpStatus.OK);
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
