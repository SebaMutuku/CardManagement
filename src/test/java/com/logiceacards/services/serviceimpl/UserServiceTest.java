package com.logiceacards.services.serviceimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.logiceacards.dto.ResponseDTO;
import com.logiceacards.dto.UserDTO;
import com.logiceacards.entities.Role;
import com.logiceacards.entities.User;
import com.logiceacards.repos.UserRepo;
import java.util.ArrayList;
import java.util.Optional;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.intercept.RunAsImplAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;


@SpringJUnitConfig
@SpringBootTest
class UserServiceTest {
    /**
     * Method under test: {@link UserService#authenticate(UserDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAuthenticate() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: A parent AuthenticationManager or a list of AuthenticationProviders is required
        //   See https://diff.blue/R013 to resolve this issue.

        ProviderManager authenticationManager = new ProviderManager(new ArrayList<>());
        TokenService tokenService = new TokenService();
        UserRepo userRepo = mock(UserRepo.class);
        UserService userService = new UserService(authenticationManager, tokenService, userRepo,
                new BCryptPasswordEncoder());
        userService.authenticate(new UserDTO("janedoe", "iloveyou"));
    }

    /**
     * Method under test: {@link UserService#authenticate(UserDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAuthenticate2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   org.springframework.security.authentication.ProviderNotFoundException: No AuthenticationProvider found for org.springframework.security.authentication.UsernamePasswordAuthenticationToken
        //       at com.logiceacards.services.serviceimpl.UserService.lambda$authenticate$0(UserService.java:36)
        //       at java.util.Optional.map(Optional.java:260)
        //       at com.logiceacards.services.serviceimpl.UserService.authenticate(UserService.java:35)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<AuthenticationProvider> providers = new ArrayList<>();
        providers.add(new RunAsImplAuthenticationProvider());
        ProviderManager authenticationManager = new ProviderManager(providers);
        UserRepo userRepo = mock(UserRepo.class);
        when(userRepo.findByUsername(Mockito.<String>any())).thenReturn(Optional.of(new User()));
        TokenService tokenService = new TokenService();
        UserService userService = new UserService(authenticationManager, tokenService, userRepo,
                new BCryptPasswordEncoder());
        userService.authenticate(new UserDTO("janedoe", "iloveyou"));
    }

    /**
     * Method under test: {@link UserService#authenticate(UserDTO)}
     */
    @Test
    void testAuthenticate3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        ArrayList<AuthenticationProvider> providers = new ArrayList<>();
        providers.add(new RunAsImplAuthenticationProvider());
        ProviderManager authenticationManager = new ProviderManager(providers);
        UserRepo userRepo = mock(UserRepo.class);
        when(userRepo.findByUsername(Mockito.<String>any())).thenReturn(Optional.empty());
        TokenService tokenService = new TokenService();
        UserService userService = new UserService(authenticationManager, tokenService, userRepo,
                new BCryptPasswordEncoder());
        ResponseDTO actualAuthenticateResult = userService.authenticate(new UserDTO("janedoe", "iloveyou"));
        assertEquals("Unauthorized. Please check your credentials", actualAuthenticateResult.message());
        assertEquals(HttpStatus.UNAUTHORIZED, actualAuthenticateResult.status());
        assertNull(actualAuthenticateResult.payload());
        verify(userRepo).findByUsername(Mockito.<String>any());
    }

    /**
     * Method under test: {@link UserService#authenticate(UserDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAuthenticate4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.logiceacards.dto.UserDTO.username()" because "request" is null
        //       at com.logiceacards.services.serviceimpl.UserService.authenticate(UserService.java:35)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<AuthenticationProvider> providers = new ArrayList<>();
        providers.add(new RunAsImplAuthenticationProvider());
        ProviderManager authenticationManager = new ProviderManager(providers);
        UserRepo userRepo = mock(UserRepo.class);
        when(userRepo.findByUsername(Mockito.<String>any())).thenReturn(Optional.of(new User()));
        TokenService tokenService = new TokenService();
        (new UserService(authenticationManager, tokenService, userRepo, new BCryptPasswordEncoder())).authenticate(null);
    }

    /**
     * Method under test: {@link UserService#createUser(User)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateUser() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: A parent AuthenticationManager or a list of AuthenticationProviders is required
        //   See https://diff.blue/R013 to resolve this issue.

        ProviderManager authenticationManager = new ProviderManager(new ArrayList<>());
        TokenService tokenService = new TokenService();
        UserRepo userRepo = mock(UserRepo.class);
        UserService userService = new UserService(authenticationManager, tokenService, userRepo,
                new BCryptPasswordEncoder());
        userService.createUser(new User());
    }

    /**
     * Method under test: {@link UserService#createUser(User)}
     */
    @Test
    void testCreateUser2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        ArrayList<AuthenticationProvider> providers = new ArrayList<>();
        providers.add(new RunAsImplAuthenticationProvider());
        ProviderManager authenticationManager = new ProviderManager(providers);
        UserRepo userRepo = mock(UserRepo.class);
        when(userRepo.findByUsername(Mockito.<String>any())).thenReturn(Optional.of(new User()));
        TokenService tokenService = new TokenService();
        UserService userService = new UserService(authenticationManager, tokenService, userRepo,
                new BCryptPasswordEncoder());
        userService.createUser(new User());
        verify(userRepo).findByUsername(Mockito.<String>any());
    }

    /**
     * Method under test: {@link UserService#createUser(User)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateUser3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: rawPassword cannot be null
        //       at com.logiceacards.services.serviceimpl.UserService.lambda$createUser$3(UserService.java:65)
        //       at java.util.Optional.orElseGet(Optional.java:364)
        //       at com.logiceacards.services.serviceimpl.UserService.createUser(UserService.java:64)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<AuthenticationProvider> providers = new ArrayList<>();
        providers.add(new RunAsImplAuthenticationProvider());
        ProviderManager authenticationManager = new ProviderManager(providers);
        UserRepo userRepo = mock(UserRepo.class);
        when(userRepo.findByUsername(Mockito.<String>any())).thenReturn(Optional.empty());
        TokenService tokenService = new TokenService();
        UserService userService = new UserService(authenticationManager, tokenService, userRepo,
                new BCryptPasswordEncoder());
        userService.createUser(new User());
    }

    /**
     * Method under test: {@link UserService#createUser(User)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateUser4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.logiceacards.entities.User.getUsername()" because "user" is null
        //       at com.logiceacards.services.serviceimpl.UserService.createUser(UserService.java:62)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<AuthenticationProvider> providers = new ArrayList<>();
        providers.add(new RunAsImplAuthenticationProvider());
        ProviderManager authenticationManager = new ProviderManager(providers);
        UserRepo userRepo = mock(UserRepo.class);
        when(userRepo.findByUsername(Mockito.<String>any())).thenReturn(Optional.of(new User()));
        TokenService tokenService = new TokenService();
        (new UserService(authenticationManager, tokenService, userRepo, new BCryptPasswordEncoder())).createUser(null);
    }

    /**
     * Method under test: {@link UserService#createUser(User)}
     */
    @Test
    void testCreateUser5() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        ArrayList<AuthenticationProvider> providers = new ArrayList<>();
        providers.add(new RunAsImplAuthenticationProvider());
        ProviderManager authenticationManager = new ProviderManager(providers);
        UserRepo userRepo = mock(UserRepo.class);
        when(userRepo.findByUsername(Mockito.<String>any())).thenReturn(Optional.of(new User()));
        TokenService tokenService = new TokenService();
        UserService userService = new UserService(authenticationManager, tokenService, userRepo,
                new BCryptPasswordEncoder());
        User user = mock(User.class);
        when(user.getUsername()).thenReturn("janedoe");
        userService.createUser(user);
        verify(userRepo).findByUsername(Mockito.<String>any());
        verify(user).getUsername();
    }

    /**
     * Method under test: {@link UserService#createUser(User)}
     */
    @Test
    void testCreateUser6() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: class org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Could not load CacheAwareContextLoaderDelegate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]
        //       at java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.NoClassDefFoundError: org/springframework/core/io/support/SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   java.lang.ClassNotFoundException: org.springframework.core.io.support.SpringFactoriesLoader$FailureHandler
        //       at java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        ArrayList<AuthenticationProvider> providers = new ArrayList<>();
        providers.add(new RunAsImplAuthenticationProvider());
        ProviderManager authenticationManager = new ProviderManager(providers);
        UserRepo userRepo = mock(UserRepo.class);
        when(userRepo.save(Mockito.<User>any())).thenReturn(new User());
        when(userRepo.findByUsername(Mockito.<String>any())).thenReturn(Optional.empty());
        TokenService tokenService = new TokenService();
        UserService userService = new UserService(authenticationManager, tokenService, userRepo,
                new BCryptPasswordEncoder());
        User user = mock(User.class);
        when(user.getRole()).thenReturn(Role.ADMIN);
        when(user.getFullName()).thenReturn("Dr Jane Doe");
        when(user.getPassword()).thenReturn("iloveyou");
        when(user.getUsername()).thenReturn("janedoe");
        userService.createUser(user);
        verify(userRepo).save(Mockito.<User>any());
        verify(userRepo).findByUsername(Mockito.<String>any());
        verify(user).getRole();
        verify(user).getFullName();
        verify(user).getPassword();
        verify(user, atLeast(1)).getUsername();
    }
}

