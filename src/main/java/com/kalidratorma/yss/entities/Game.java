package com.kalidratorma.yss.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalTime;


/**
 * Игра
 */
@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "game")
public class Game {

    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "game_seq")
    @SequenceGenerator(
            name = "game_seq",
            sequenceName = "game_seq",
            allocationSize = 1
    )
    @Column(name = "id", updatable = false)
    private Long id;

    /**
     * Турнир
     */
    @ManyToOne
    private Tournament tournament;

    /**
     * Дата игры
     */
    @Temporal(TemporalType.DATE)
    private LocalDate date;

    /**
     * Время игры (начало)
     */
    @Temporal(TemporalType.TIME)
    private LocalTime time;

    /**
     * Сезон
     */
    @OneToOne
    private Season season;

    /**
     * Адрес арены
     */
    private String arenaAddress;

    /**
     * Год команды
     */
    @OneToOne
    private TeamYear teamYear;

    /**
     * Команда Соперника
     */
    @OneToOne
    private ClubTeam clubTeam;
}
