package com.logiceacards.dto;

import lombok.ToString;

import java.util.Optional;


@ToString
public record CardDTO(String cardName, Optional<String> cardColor) {
}
