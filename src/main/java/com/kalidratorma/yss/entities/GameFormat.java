package com.kalidratorma.yss.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Table(name = "game_format")
public class GameFormat {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="game_format_seq")
    @SequenceGenerator(
            name="game_format_seq",
            sequenceName="game_format_seq",
            allocationSize=1
    )
    @Column(name = "id", updatable = false)
    private Long id;

    /**
     * Название формата
     */
    private String name;

    /**
     * Тип льда
     */
    private IceType iceType;

    /**
     * Количество игроков в команде
     */
    private byte numberOfPlayers;
}
