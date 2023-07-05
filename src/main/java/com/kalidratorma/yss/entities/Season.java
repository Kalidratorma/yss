package com.kalidratorma.yss.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Сезон
 */
@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "season", indexes = {@Index(name = "IDX_SEASON_SEASON", columnList = "season")})
public class Season {

    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "position_seq")
    @SequenceGenerator(
            name = "position_seq",
            sequenceName = "position_seq",
            allocationSize = 1
    )
    @Column(name = "id", updatable = false)
    private Long id;

    /**
     * Сезон
     */
    @Column(nullable = false, unique = true)
    private short season;

    @Override
    public String toString() {
        return "Season{" +
                "id=" + id +
                ", season=" + season + "-" + (season+1) +
                '}';
    }
}
