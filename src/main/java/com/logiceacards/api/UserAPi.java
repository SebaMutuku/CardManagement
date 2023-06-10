package com.logiceacards.api;

import com.logiceacards.dto.ResponseDTO;
import com.logiceacards.dto.UserDTO;
import com.logiceacards.entities.User;
import com.logiceacards.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/security/", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@RequiredArgsConstructor
public class UserAPi {
    private final UserService userService;


    @GetMapping(value = "/authenticate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> authenticate(@RequestBody UserDTO request) {
        ResponseDTO body = userService.authenticate(request);
        log.info("Create student response --> [{}]", body);
        return new ResponseEntity<>(body, body.status());
    }
}
