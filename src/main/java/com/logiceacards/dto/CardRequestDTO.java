package com.logiceacards.dto;

import jakarta.validation.constraints.Null;


public record CardRequestDTO(String cardName, @Null String cardColor, @Null long userId, @Null long cardId, @Null String cardStatus, @Null String createdOn) {
}
