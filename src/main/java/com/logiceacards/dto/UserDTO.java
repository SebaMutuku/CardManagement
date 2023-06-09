package com.logiceacards.dto;


import lombok.ToString;

@ToString
public record UserDTO(String username, String password) {
}
