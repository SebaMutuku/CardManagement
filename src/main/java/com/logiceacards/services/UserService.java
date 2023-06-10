package com.logiceacards.services;


import com.logiceacards.dto.ResponseDTO;
import com.logiceacards.dto.UserDTO;
import com.logiceacards.entities.Role;
import com.logiceacards.entities.User;
import com.logiceacards.repos.UserRepo;
import com.logiceacards.services.serviceimpl.AbstractUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class UserService extends AbstractUser {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserService(@Autowired UserRepo userRepo, @Autowired AuthenticationManager authenticationManager, @Autowired TokenService tokenService, @Autowired PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public ResponseDTO authenticate(UserDTO request) {
        log.info("Request --> [{}]", request);
        Optional<User> user = userRepo.findByUsernameAndPassword(request.username(), request.password());
        var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.username(), request.password()));
        if (authentication.isAuthenticated()) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("username", authentication.getPrincipal());
            var token = tokenService.generateToken(claims, user.get());
            if (token != null) {
                var userToSave = user.get();
                userToSave.setToken(token);
                userToSave.setLastLogin(new Date());
                userRepo.save(userToSave);
                ResponseDTO response = new ResponseDTO(token, "Success", HttpStatus.OK);
                log.info("Response --> [{}]", response);
                return response;
            }
            ResponseDTO response = new ResponseDTO(token, "Please check your credentials", HttpStatus.OK);
            log.info("Response --> [{}]", response);
            return response;
        }
        ResponseDTO response = new ResponseDTO(null, "Unauthorized. Please check your credentials", HttpStatus.UNAUTHORIZED);
        log.info("Response --> [{}]", response);
        return response;
    }

    @Override
    public ResponseDTO createUser(User user) {
        log.info("Request --> [{}]", user);
        return userRepo.findByUsername(user.getUsername()).
                map(foundUser -> new ResponseDTO(foundUser, "User exists", HttpStatus.ALREADY_REPORTED))
                .orElseGet(() -> {
                    var userInstance = User.builder().username(user.getUsername()).password(passwordEncoder.encode(user.getPassword()))
                            .createdOn(new Date()).fullName(user.getFullName()).role(user.getRole())
                            .build();
                    userRepo.save(userInstance);
                    ResponseDTO response = new ResponseDTO(userInstance, "Successfully added user " + user.getUsername(), HttpStatus.CREATED);
                    log.info("Response --> [{}]", response);
                    return response;
                });
    }

}
