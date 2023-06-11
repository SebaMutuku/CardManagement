package com.logiceacards.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
    @Column(name = "cardId", unique = true, nullable = false)
    private Long cardId;
    @Column(nullable = false)
    private String cardName;
    @Column
    private String cardColor;

    @Column
    private String cardStatus;

    @Column
    @Temporal(TemporalType.DATE)
    private Date createdOn;

    @JoinColumn(name = "userId", nullable = false, referencedColumnName = "userId", table = "User")
    private Long userId;

}
