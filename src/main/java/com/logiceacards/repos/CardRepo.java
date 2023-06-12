package com.logiceacards.repos;


import com.logiceacards.entities.Card;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepo extends JpaRepository<Card, Long> {
    Optional<Card> findByCardIdAndUserId(long cardId, long userId);

    Optional<Card> findByCardName(String cardName);

    List<Card> findByUserIdOrCardNameOrCreatedOnOrCardStatusOrCardColorOrderByCardNameAscCardColorAscCardStatusDescCreatedOnDesc(long userId, String cardName, Date createdOn, String Status, String cardColor, Pageable pageable);

}
