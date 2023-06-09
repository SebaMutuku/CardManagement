package com.logiceacards.api;

import com.logiceacards.dto.ResponseDTO;
import com.logiceacards.dto.UserDTO;
import com.logiceacards.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/security/", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class UserAPi {
    private final UserService userService;

    public UserAPi(@Autowired UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/authenticate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> authenticate(@RequestBody UserDTO request) {
        ResponseDTO body = userService.authenticate(request);
        log.info("Create student response --> [{}]", body);
        return new ResponseEntity<>(body, body.status());
    }
}
