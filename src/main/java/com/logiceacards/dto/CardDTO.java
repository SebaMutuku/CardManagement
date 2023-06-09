package com.logiceacards.dto;

import java.util.Optional;

public record CardDTO(String cardName, Optional<String> cardColor) {
}
