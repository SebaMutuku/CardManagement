package com.logiceacards.services;


import com.logiceacards.dto.ResponseDTO;
import com.logiceacards.dto.UserDTO;
import com.logiceacards.entities.User;
import com.logiceacards.repos.UserRepo;
import com.logiceacards.services.serviceimpl.AbstractUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class UserService extends AbstractUser {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UserRepo userRepo;

    public UserService(@Autowired UserRepo userRepo, @Autowired AuthenticationManager authenticationManager, @Autowired TokenService tokenService) {
        this.userRepo = userRepo;
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    @Transactional
    public ResponseDTO authenticate(UserDTO request) {
        Optional<User> user = userRepo.findByUsernameAndPassword(request.username(), request.password());
        var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.username(), request.password()));
        if (authentication.isAuthenticated()) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("username", authentication.getPrincipal());
            var token = tokenService.generateToken(claims, user.get());
            return new ResponseDTO(token, "Success", HttpStatus.OK);
        }
        return new ResponseDTO(null, "User not found", HttpStatus.UNAUTHORIZED);
    }

}
