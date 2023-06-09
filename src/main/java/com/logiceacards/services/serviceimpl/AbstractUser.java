package com.logiceacards.services.serviceimpl;

import com.logiceacards.dto.ResponseDTO;
import com.logiceacards.dto.UserDTO;

public abstract class AbstractUser {
    protected abstract ResponseDTO authenticate(UserDTO request);
}
