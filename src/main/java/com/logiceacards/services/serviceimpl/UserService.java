package com.logiceacards.services.serviceimpl;


import com.logiceacards.dto.ResponseDTO;
import com.logiceacards.dto.UserDTO;
import com.logiceacards.entities.Role;
import com.logiceacards.entities.User;
import com.logiceacards.repos.UserRepo;
import com.logiceacards.services.AbstractUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService extends AbstractUser {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public ResponseDTO authenticate(UserDTO request) {
        return userRepo.findByUsername(request.username()).map(user -> {
            var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.username(), request.password()));
            if (authentication.isAuthenticated()) {
                Map<String, Object> claims = new HashMap<>();
                claims.put("username", authentication.getPrincipal());
                var token = tokenService.generateToken(claims, user);
                if (token != null) {
                    user.setToken(token);
                    user.setLastLogin(new Date());
                    userRepo.save(user);
                    ResponseDTO response = new ResponseDTO(token, "Authentication successful", HttpStatus.OK);
                    log.info("Response --> [{}]", response);
                    return response;
                }
                ResponseDTO response = new ResponseDTO(null, "Please check your credentials", HttpStatus.OK);
                log.info("Response --> [{}]", response);
                return response;
            }
            return new ResponseDTO(null, "Unauthorized. Please check your credentials", HttpStatus.UNAUTHORIZED);
        }).orElseGet(() -> new ResponseDTO(null, "Unauthorized. Please check your credentials", HttpStatus.UNAUTHORIZED));

    }

    @Override
    public void createUser(User user) {
        log.info("Create user request --> [{}]", user);
        userRepo.findByUsername(user.getUsername()).
                map(foundUser -> new ResponseDTO(foundUser, "User exists", HttpStatus.ALREADY_REPORTED))
                .orElseGet(() -> {
                    var userInstance = User.builder().username(user.getUsername()).password(passwordEncoder.encode(user.getPassword()))
                            .createdOn(new Date()).fullName(user.getFullName()).role(Role.valueOf(user.getRole().name()))
                            .build();
                    userRepo.save(userInstance);
                    ResponseDTO response = new ResponseDTO(userInstance, "Successfully added user " + user.getUsername(), HttpStatus.CREATED);
                    log.info("Create user response --> [{}]", response);
                    return response;
                });
    }

}
