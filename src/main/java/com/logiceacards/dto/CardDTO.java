package com.logiceacards.dto;

import lombok.ToString;

import java.util.Optional;


public record CardDTO(String cardName, Optional<String> cardColor) {
}
