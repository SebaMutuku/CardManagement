package com.logiceacards.dto;

import jakarta.validation.constraints.Null;


public record CardDTO(String cardName, @Null String cardColor, @Null Long userId, @Null Long cardId, @Null String cardStatus) {
}
