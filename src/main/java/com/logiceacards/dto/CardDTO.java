package com.logiceacards.dto;

import jakarta.validation.constraints.Null;
import lombok.NonNull;


public record CardDTO(String cardName, @Null String cardColor, @NonNull Long userId) {
}
