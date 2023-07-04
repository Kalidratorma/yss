package com.kalidratorma.yss.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * Формат игры
 */
@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Table(name = "game_format",
        indexes = {@Index(name = "IDX_GAME_FORMAT_NAME", columnList = "name")})
public class GameFormat {

    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "game_format_seq")
    @SequenceGenerator(
            name = "game_format_seq",
            sequenceName = "game_format_seq",
            allocationSize = 1
    )
    @Column(name = "id", updatable = false)
    private Long id;

    /**
     * Название формата
     */
    @Column(unique = true)
    private String name;

    /**
     * Коммерческая/некоммерческая игра
     */
    private Boolean isCommercial;

    /**
     * Тип льда
     */
    private IceType iceType;

    /**
     * Количество игроков в команде
     */
    private byte numberOfPlayers;
}
