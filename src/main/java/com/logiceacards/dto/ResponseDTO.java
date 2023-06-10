package com.logiceacards.dto;

import lombok.ToString;
import org.springframework.http.HttpStatus;


public record ResponseDTO(Object payload, String message, HttpStatus status) {
}
