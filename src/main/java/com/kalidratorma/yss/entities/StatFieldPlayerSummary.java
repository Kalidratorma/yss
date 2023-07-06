package com.kalidratorma.yss.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

/**
 * Сводная статистика спортсмена
 */
@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Table(name = "stat_field_player_summary")
public class StatFieldPlayerSummary {

    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stat_field_player_summary_seq")
    @SequenceGenerator(
            name = "stat_field_player_summary_seq",
            sequenceName = "stat_field_player_summary_seq",
            allocationSize = 1
    )
    @Column(name = "id", updatable = false)
    private Long id;

    /**
     * Спортсмен
     */
    @ManyToOne
    private Player player;

    /**
     * Дата
     */
    @Temporal(TemporalType.DATE)
    private LocalDate date;

    /**
     * И (проведённые игры) / GP (Games played)
     */
    private int gamesPlayed;

    /**
     * Г (голы), или Ш (шайбы) / G (Goals)
     */
    private int goals;

    /**
     * П (голевые пасы / передачи), или А (ассистировал) / A (Assists)
     */
    private int assists;

    /**
     * О (очки) / Pts (Points) — сумма голов и голевых пасов
     */
    private int points;

    /**
     * Штр (штрафное время в минутах) / PIM (Penalties in minutes)
     */
    private int penalties;

    /**
     * ГБ (голы в большинстве) / PPG (Power play goals)
     */
    private int powerPlayGoals;

    /**
     * ПБ (голевые пасы в большинстве) / PPA (Power play assists)
     */
    private int powerPlayAssists;

    /**
     * ГМ (голы в меньшинстве) / SHG (Shorthanded goals)
     */
    private int shorthandedGoals;

    /**
     * ПМ (голевые пасы в меньшинстве) / SHA (Shorthanded assists)
     */
    private int shorthandedAssists;

    /**
     * ПГ (победные голы) / GWG (Game-winning goals) — решающие голы, например третий гол победителя при счёте 2:2.
     */
    private int gameWinningGoals;

    /**
     * РБ (решающие послематчевые буллиты) / GTG (Game-tying goals)
     */
    private int gameTyingGoals;

    /**
     * ПВ (голы в пустые ворота) / ENG (Empty net goals)
     */
    private int emptyNetGoals;

    /**
     * +/- (плюс/минус, показатель полезности) / P/M (Plus/minus)
     */
    private int plusMinus;

    /**
     * ВП (общее время на площадке) / TOI (Time on ice)
     */
    private int timeOnIce;

    /**
     * ВП/И (среднее время на площадке за игру) / ATOI (Average time on ice)
     */
    private int averageTimeOnIce;

    /**
     * См/И — Среднее количество смен за игру / Average Shifts Per Game
     */
    private int averageShiftsPerGame;

    /**
     * БВ (броски по воротам) / SOG (Shots on goal)
     */
    private int shotsOnGoal;

    /**
     * %БВ (процент реализованных бросков) / Shooting Percentage
     */
    private int shootingPercentage;

    /**
     * БВ/И (среднее количество бросков по воротам за игру) / SOG/G - Shots on Goal per Game
     */
    private int shotsOnGoalPerGame;

    /**
     * Вбр (вбрасывания) / FO - Faceoffs
     */
    private int faceoffs;

    /**
     * %Вбр (процент выигранных вбрасываний) / Faceoff Wins
     */
    private int faceoffWins;
}
