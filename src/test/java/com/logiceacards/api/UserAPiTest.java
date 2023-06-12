package com.logiceacards.api;

import com.logiceacards.dto.ResponseDTO;
import com.logiceacards.dto.UserDTO;
import com.logiceacards.repos.UserRepo;
import com.logiceacards.services.serviceimpl.TokenService;
import com.logiceacards.services.serviceimpl.UserService;
import java.util.ArrayList;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringJUnitConfig
@SpringBootTest
class UserAPiTest {
    @MockBean
    private UserRepo userRepo;

    @Test
    public void testAuthenticateSuccessfully() {
        TokenService tokenService = new TokenService();
        UserApi userAPi = new UserApi(
                new UserService(mock(ProviderManager.class), tokenService, userRepo, new BCryptPasswordEncoder()));
        userAPi.authenticate(new UserDTO("logicea", "pass123"));
    }

    @Test
    public void testAuthenticateReturnsBadCredentials() {
        ArrayList<AuthenticationProvider> providers = new ArrayList<>();
        providers.add(new DaoAuthenticationProvider());
        ProviderManager authenticationManager = new ProviderManager(providers);
        when(userRepo.findByUsername(Mockito.any())).thenReturn(Optional.empty());
        TokenService tokenService = new TokenService();
        UserApi userAPi = new UserApi(
                new UserService(authenticationManager, tokenService, userRepo, new BCryptPasswordEncoder()));
        ResponseEntity<ResponseDTO> actualAuthenticateResult = userAPi.authenticate(new UserDTO("logicea", "pass123"));
        assertTrue(actualAuthenticateResult.hasBody());
        assertTrue(actualAuthenticateResult.getHeaders().isEmpty());
        assertEquals(401, actualAuthenticateResult.getStatusCode().value());
        ResponseDTO body = actualAuthenticateResult.getBody();
        assertNull(body.payload());
        assertEquals("Unauthorized. Please check your credentials", body.message());
        assertEquals(HttpStatus.UNAUTHORIZED, body.status());
        verify(userRepo).findByUsername(Mockito.any());
    }

    @Test
    public void testAuthenticateReturnsCorrectMessage() {
        UserService userService = mock(UserService.class);
        when(userService.authenticate(Mockito.any()))
                .thenReturn(new ResponseDTO("Payload", "Not all who wander are lost", HttpStatus.CONTINUE));
        UserApi userAPi = new UserApi(userService);
        ResponseEntity<ResponseDTO> actualAuthenticateResult = userAPi.authenticate(new UserDTO("logicea", "pass123"));
        assertTrue(actualAuthenticateResult.hasBody());
        assertTrue(actualAuthenticateResult.getHeaders().isEmpty());
        assertEquals(100, actualAuthenticateResult.getStatusCode().value());
        verify(userService).authenticate(Mockito.any());
    }

}

