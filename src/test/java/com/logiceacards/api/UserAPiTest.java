package com.logiceacards.api;

import com.logiceacards.dto.ResponseDTO;
import com.logiceacards.dto.UserDTO;
import com.logiceacards.entities.User;
import com.logiceacards.repos.UserRepo;
import com.logiceacards.services.serviceimpl.TokenService;
import com.logiceacards.services.serviceimpl.UserService;
import java.util.ArrayList;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.intercept.RunAsImplAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringJUnitConfig
@SpringBootTest
class UserAPiTest {
    @Test
    void testAuthenticate() {
        TokenService tokenService = new TokenService();
        UserRepo userRepo = mock(UserRepo.class);
        UserApi userAPi = new UserApi(
                new UserService(mock(ProviderManager.class), tokenService, userRepo, new BCryptPasswordEncoder()));
        userAPi.authenticate(new UserDTO("janedoe", "iloveyou"));
    }

    @Test
    void testAuthenticate2() {
        ArrayList<AuthenticationProvider> providers = new ArrayList<>();
        providers.add(new RunAsImplAuthenticationProvider());
        ProviderManager authenticationManager = new ProviderManager(providers);
        UserRepo userRepo = mock(UserRepo.class);
        when(userRepo.findByUsername(Mockito.any())).thenReturn(Optional.of(new User()));
        TokenService tokenService = new TokenService();
        UserApi userAPi = new UserApi(
                new UserService(authenticationManager, tokenService, userRepo, new BCryptPasswordEncoder()));
        userAPi.authenticate(new UserDTO("logiceas", "pass123"));
    }

    @Test
    void testAuthenticate3() {
        ArrayList<AuthenticationProvider> providers = new ArrayList<>();
        providers.add(new RunAsImplAuthenticationProvider());
        ProviderManager authenticationManager = new ProviderManager(providers);
        UserRepo userRepo = mock(UserRepo.class);
        when(userRepo.findByUsername(Mockito.any())).thenReturn(Optional.empty());
        TokenService tokenService = new TokenService();
        UserApi userAPi = new UserApi(
                new UserService(authenticationManager, tokenService, userRepo, new BCryptPasswordEncoder()));
        ResponseEntity<ResponseDTO> actualAuthenticateResult = userAPi.authenticate(new UserDTO("janedoe", "iloveyou"));
        assertTrue(actualAuthenticateResult.hasBody());
        assertTrue(actualAuthenticateResult.getHeaders().isEmpty());
        assertEquals(401, actualAuthenticateResult.getStatusCodeValue());
        ResponseDTO body = actualAuthenticateResult.getBody();
        assertNull(body.payload());
        assertEquals("Unauthorized. Please check your credentials", body.message());
        assertEquals(HttpStatus.UNAUTHORIZED, body.status());
        verify(userRepo).findByUsername(Mockito.any());
    }

    @Test
    void testAuthenticate4() {
        UserService userService = mock(UserService.class);
        when(userService.authenticate(Mockito.any()))
                .thenReturn(new ResponseDTO("Payload", "Not all who wander are lost", HttpStatus.CONTINUE));
        UserApi userAPi = new UserApi(userService);
        ResponseEntity<ResponseDTO> actualAuthenticateResult = userAPi.authenticate(new UserDTO("janedoe", "iloveyou"));
        assertTrue(actualAuthenticateResult.hasBody());
        assertTrue(actualAuthenticateResult.getHeaders().isEmpty());
        assertEquals(100, actualAuthenticateResult.getStatusCodeValue());
        verify(userService).authenticate(Mockito.any());
    }

}

