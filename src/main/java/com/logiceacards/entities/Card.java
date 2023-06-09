package com.logiceacards.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
    private Long cardId;
    @Column(nullable = false)
    private String cardName;
    @Column
    private String cardColor;
    @Column(columnDefinition = "varchar(50) default 'To Do'")
    private String cardStatus;
    @Column(columnDefinition = "varchar(50) default 'To Do'")
    @JoinColumn(name = "userId", nullable = false, referencedColumnName = "userId", table = "User")
    private Long userId;

}
