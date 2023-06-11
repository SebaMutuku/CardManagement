package com.logiceacards.repos;


import com.logiceacards.entities.Card;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface CardRepo extends JpaRepository<Card, Long> {
    Optional<Card> findByUserId(long userId);

    Optional<Card> findByCardName(String cardName);
    Optional<Card>findByCardNameContainingIgnoreCaseAndCardStatusContainingIgnoreCaseAndCreatedOnAndCardColorContainingIgnoreCase(String cardName, String cardColor, Date createdOn, String Status, Pageable pageable);

}
