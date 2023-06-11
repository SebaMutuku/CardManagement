package com.logiceacards.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.logiceacards.dto.ResponseDTO;
import com.logiceacards.dto.UserDTO;
import com.logiceacards.entities.User;
import com.logiceacards.repos.UserRepo;
import com.logiceacards.services.serviceimpl.TokenService;
import com.logiceacards.services.serviceimpl.UserService;
import java.util.ArrayList;
import java.util.Optional;
import org.junit.jupiter.api.Disabled;
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

@SpringJUnitConfig
@SpringBootTest
class UserAPiTest {
    /**
     * Method under test: {@link UserAPi#authenticate(UserDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAuthenticate() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "Object.getClass()" because "bean" is null
        //   See https://diff.blue/R013 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: A parent AuthenticationManager or a list of AuthenticationProviders is required
        //   See https://diff.blue/R013 to resolve this issue.

        ProviderManager authenticationManager = new ProviderManager(new ArrayList<>());
        TokenService tokenService = new TokenService();
        UserRepo userRepo = mock(UserRepo.class);
        UserAPi userAPi = new UserAPi(
                new UserService(authenticationManager, tokenService, userRepo, new BCryptPasswordEncoder()));
        userAPi.authenticate(new UserDTO("janedoe", "iloveyou"));
    }

    /**
     * Method under test: {@link UserAPi#authenticate(UserDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAuthenticate2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "Object.getClass()" because "bean" is null
        //   See https://diff.blue/R013 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   org.springframework.security.authentication.ProviderNotFoundException: No AuthenticationProvider found for org.springframework.security.authentication.UsernamePasswordAuthenticationToken
        //       at com.logiceacards.services.serviceimpl.UserService.lambda$authenticate$0(UserService.java:36)
        //       at java.util.Optional.map(Optional.java:260)
        //       at com.logiceacards.services.serviceimpl.UserService.authenticate(UserService.java:35)
        //       at com.logiceacards.api.UserAPi.authenticate(UserAPi.java:23)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<AuthenticationProvider> providers = new ArrayList<>();
        providers.add(new RunAsImplAuthenticationProvider());
        ProviderManager authenticationManager = new ProviderManager(providers);
        UserRepo userRepo = mock(UserRepo.class);
        when(userRepo.findByUsername(Mockito.<String>any())).thenReturn(Optional.of(new User()));
        TokenService tokenService = new TokenService();
        UserAPi userAPi = new UserAPi(
                new UserService(authenticationManager, tokenService, userRepo, new BCryptPasswordEncoder()));
        userAPi.authenticate(new UserDTO("janedoe", "iloveyou"));
    }

    /**
     * Method under test: {@link UserAPi#authenticate(UserDTO)}
     */
    @Test
    void testAuthenticate3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "Object.getClass()" because "bean" is null
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<AuthenticationProvider> providers = new ArrayList<>();
        providers.add(new RunAsImplAuthenticationProvider());
        ProviderManager authenticationManager = new ProviderManager(providers);
        UserRepo userRepo = mock(UserRepo.class);
        when(userRepo.findByUsername(Mockito.<String>any())).thenReturn(Optional.empty());
        TokenService tokenService = new TokenService();
        UserAPi userAPi = new UserAPi(
                new UserService(authenticationManager, tokenService, userRepo, new BCryptPasswordEncoder()));
        ResponseEntity<ResponseDTO> actualAuthenticateResult = userAPi.authenticate(new UserDTO("janedoe", "iloveyou"));
        assertTrue(actualAuthenticateResult.hasBody());
        assertTrue(actualAuthenticateResult.getHeaders().isEmpty());
        assertEquals(401, actualAuthenticateResult.getStatusCodeValue());
        ResponseDTO body = actualAuthenticateResult.getBody();
        assertNull(body.payload());
        assertEquals("Unauthorized. Please check your credentials", body.message());
        assertEquals(HttpStatus.UNAUTHORIZED, body.status());
        verify(userRepo).findByUsername(Mockito.<String>any());
    }

    /**
     * Method under test: {@link UserAPi#authenticate(UserDTO)}
     */
    @Test
    void testAuthenticate4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "Object.getClass()" because "bean" is null
        //   See https://diff.blue/R013 to resolve this issue.

        UserService userService = mock(UserService.class);
        when(userService.authenticate(Mockito.<UserDTO>any()))
                .thenReturn(new ResponseDTO("Payload", "Not all who wander are lost", HttpStatus.CONTINUE));
        UserAPi userAPi = new UserAPi(userService);
        ResponseEntity<ResponseDTO> actualAuthenticateResult = userAPi.authenticate(new UserDTO("janedoe", "iloveyou"));
        assertTrue(actualAuthenticateResult.hasBody());
        assertTrue(actualAuthenticateResult.getHeaders().isEmpty());
        assertEquals(100, actualAuthenticateResult.getStatusCodeValue());
        verify(userService).authenticate(Mockito.<UserDTO>any());
    }

    /**
     * Method under test: {@link UserAPi#authenticate(UserDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAuthenticate5() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "Object.getClass()" because "bean" is null
        //   See https://diff.blue/R013 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.logiceacards.dto.ResponseDTO.status()" because "body" is null
        //       at com.logiceacards.api.UserAPi.authenticate(UserAPi.java:25)
        //   See https://diff.blue/R013 to resolve this issue.

        UserService userService = mock(UserService.class);
        when(userService.authenticate(Mockito.<UserDTO>any())).thenReturn(null);
        UserAPi userAPi = new UserAPi(userService);
        userAPi.authenticate(new UserDTO("janedoe", "iloveyou"));
    }
}

