package com.logiceacards.dto;

import lombok.ToString;
import org.apache.naming.StringManager;
import org.springframework.http.HttpStatus;

@ToString
public record ResponseDTO(Object payload, String message, HttpStatus status) {
}
