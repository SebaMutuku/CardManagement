package com.logiceacards.services.serviceimpl;

import com.logiceacards.dto.ResponseDTO;
import com.logiceacards.dto.UserDTO;
import com.logiceacards.entities.Role;
import com.logiceacards.entities.User;
import com.logiceacards.repos.UserRepo;
import java.util.ArrayList;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.intercept.RunAsImplAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringJUnitConfig
@SpringBootTest
class UserServiceTest {

    @MockBean
    private UserRepo userRepo;

    @Test
    public void testAuthenticateAuthenticatesSuccessfully() {
        ArrayList<AuthenticationProvider> providers = new ArrayList<>();
        providers.add(new RunAsImplAuthenticationProvider());
        ProviderManager authenticationManager = new ProviderManager(providers);
        when(userRepo.findByUsername(Mockito.anyString())).thenReturn(Optional.of(new User()));
        TokenService tokenService = new TokenService();
        UserService userService = new UserService(authenticationManager, tokenService, userRepo,
                new BCryptPasswordEncoder());
        UserDTO request = new UserDTO("created", "user123");
        userService.authenticate(request);
        verify(userService).authenticate(Mockito.any());
    }

    @Test
    public void testAuthenticateReturnsUnAuthorizedAccess() {
        ArrayList<AuthenticationProvider> providers = new ArrayList<>();
        providers.add(new RunAsImplAuthenticationProvider());
        ProviderManager authenticationManager = new ProviderManager(providers);
        when(userRepo.findByUsername(Mockito.anyString())).thenReturn(Optional.empty());
        TokenService tokenService = new TokenService();
        UserService userService = new UserService(authenticationManager, tokenService, userRepo,
                new BCryptPasswordEncoder());
        ResponseDTO actualAuthenticateResult = userService.authenticate(new UserDTO("created", "user123"));
        assertEquals("Unauthorized. Please check your credentials", actualAuthenticateResult.message());
        assertEquals(HttpStatus.UNAUTHORIZED, actualAuthenticateResult.status());
        assertNull(actualAuthenticateResult.payload());
        verify(userRepo).findByUsername(Mockito.anyString());
    }


    @Test
    public void testCreateUserWithNullUer() {
        ArrayList<AuthenticationProvider> providers = new ArrayList<>();
        providers.add(new RunAsImplAuthenticationProvider());
        ProviderManager authenticationManager = new ProviderManager(providers);
        when(userRepo.findByUsername(Mockito.anyString())).thenReturn(Optional.of(new User()));
        TokenService tokenService = new TokenService();
        (new UserService(authenticationManager, tokenService, userRepo, new BCryptPasswordEncoder())).createUser(null);
    }

    @Test
    public void testCreateUserWithANonNullUser() {
        ArrayList<AuthenticationProvider> providers = new ArrayList<>();
        providers.add(new RunAsImplAuthenticationProvider());
        ProviderManager authenticationManager = new ProviderManager(providers);
        when(userRepo.findByUsername(Mockito.anyString())).thenReturn(Optional.of(new User()));
        TokenService tokenService = new TokenService();
        UserService userService = new UserService(authenticationManager, tokenService, userRepo,
                new BCryptPasswordEncoder());
        User user = mock(User.class);
        when(user.getUsername()).thenReturn("sampleUsername");
        userService.createUser(user);
        verify(userRepo).findByUsername(Mockito.anyString());
        verify(user).getUsername();
    }

    @Test
    public void testCreateUserWithAUserAndPassword() {
        ArrayList<AuthenticationProvider> providers = new ArrayList<>();
        providers.add(new RunAsImplAuthenticationProvider());
        ProviderManager authenticationManager = new ProviderManager(providers);
        when(userRepo.save(Mockito.any())).thenReturn(new User());
        when(userRepo.findByUsername(Mockito.anyString())).thenReturn(Optional.empty());
        TokenService tokenService = new TokenService();
        UserService userService = new UserService(authenticationManager, tokenService, userRepo,
                new BCryptPasswordEncoder());
        User user = mock(User.class);
        when(user.getRole()).thenReturn(Role.ADMIN);
        when(user.getFullName()).thenReturn("created user");
        when(user.getPassword()).thenReturn("created");
        when(user.getUsername()).thenReturn("user123");
        userService.createUser(user);
        verify(userRepo).save(Mockito.any());
        verify(userRepo).findByUsername(Mockito.anyString());
        verify(user).getRole();
        verify(user).getFullName();
        verify(user).getPassword();
        verify(user, atLeast(1)).getUsername();
    }
}

