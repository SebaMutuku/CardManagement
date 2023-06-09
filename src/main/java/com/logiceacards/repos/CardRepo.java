package com.logiceacards.repos;


import com.logiceacards.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepo extends JpaRepository<Card, Long> {
    @Override
    Optional<Card> findById(Long aLong);
    Optional<Card> findByUserId(Long userId);
}
