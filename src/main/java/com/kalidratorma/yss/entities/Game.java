package com.kalidratorma.yss.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;


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
    @ManyToOne
    private Season season;

    /**
     * Адрес арены
     */
    private String arenaAddress;

    /**
     * Год команды
     */
    @ManyToOne
    private TeamYear teamYear;

    /**
     * Команда Соперника
     */
    @ManyToOne
    private ClubTeam clubTeam;

    /**
     * Спортсмены
     */
    @ManyToMany
    @JoinTable(name = "game_player",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "player_id"))
    private Set<Player> players;
}
