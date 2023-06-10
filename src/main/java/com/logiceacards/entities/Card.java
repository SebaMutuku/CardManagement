package com.logiceacards.entities;


import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "Card")
@ToString
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cardId;
    @Column(nullable = false)
    private String cardName;
    @Column
    private String cardColor;

    @Column
    private String cardStatus;

    @JoinColumn(name = "userId", nullable = false, referencedColumnName = "userId", table = "User")
    private Long userId;

}
