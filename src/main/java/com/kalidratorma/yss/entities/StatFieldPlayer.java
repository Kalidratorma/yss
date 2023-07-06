package com.kalidratorma.yss.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

/**
 * Статистика спортсмена за игру
 */
@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Table(name = "stat_field_player")
public class StatFieldPlayer {

    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stat_field_player_seq")
    @SequenceGenerator(
            name = "stat_field_player_seq",
            sequenceName = "stat_field_player_seq",
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
     * Игра
     */
    @ManyToOne
    private Game game;

    /**
     * Дата
     */
    @Temporal(TemporalType.DATE)
    private LocalDate date;

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
     * ПГ (забил победный гол) / GWG (Game-winning goal) — решающие гол, например третий гол победителя при счёте 2:2.
     */
    private boolean isGameWinningGoals;

    /**
     * РБ (забил решающий послематчевый буллит) / GTG (Game-tying goal)
     */
    private boolean isGameTyingGoals;

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
     * Количество смен / Shifts
     */
    private int shifts;

    /**
     * БВ (броски по воротам) / SOG (Shots on goal)
     */
    private int shotsOnGoal;

    /**
     * %БВ (процент реализованных бросков) / Shooting Percentage
     */
    private float shootingPercentage;

    /**
     * Вбр (вбрасывания) / FO - Faceoffs
     */
    private int faceoffs;

    /**
     * ВВбр (Количество выигранных вбрасываний) / Faceoff Wins
     */
    private int faceoffWins;

    @PreUpdate
    @PrePersist
    public void calc() {
        points = goals + assists;
        shootingPercentage = (float) goals /shotsOnGoal * 100;
    }
}
