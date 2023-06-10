package com.logiceacards.services.serviceimpl;

import com.logiceacards.dto.ResponseDTO;
import com.logiceacards.dto.UserDTO;
import com.logiceacards.entities.User;

public abstract class AbstractUser {
    protected abstract ResponseDTO authenticate(UserDTO request);

    protected abstract ResponseDTO createUser(User user);
}
