package com.kalidratorma.yss.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

/**
 * Общая статистика команды за сезон
 */
@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Table(name = "stat_team"
        , uniqueConstraints = { @UniqueConstraint(columnNames = {"team_id", "season_id"})}
        , indexes = {@Index(name = "IDX_STAT_TEAM_TEAM_ID_SEASON_ID", columnList = "team_id, season_id")}
)
public class StatTeam {

    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stat_team_seq")
    @SequenceGenerator(
            name = "stat_team_seq",
            sequenceName = "stat_team_seq",
            allocationSize = 1
    )
    @Column(name = "id", updatable = false)
    private Long id;

    /**
     * Команда
     */
    @ManyToOne
    private Team team;

    /**
     * Сезон
     */
    @ManyToOne
    private Season season;

    /**
     * Количество проведенных игр
     */
    private int gamesPlayed;

    /**
     * Количество побед в основное время
     */
    private int wins;

    /**
     * Количество поражений в основное время
     */
    private int losses;

    /**
     * Количество ничьих
     */
    private int ties;

    /**
     * Количество выигрышей в овертайме
     */
    private int overtimeWins;

    /**
     * Количество выигрышей по послематчевым буллитам
     */
    private int shootoutWins;

    /**
     * Количество проигрышей в овертайме
     */
    private int overtimeLosses;

    /**
     * Количество проигрышей по послематчевым буллитам
     */
    private int shootoutLosses;

    /**
     * Общее количество набранных очков
     */
    private int points;

    /**
     * Количество заброшенных шайб
     */
    private int goalsFor;

    /**
     * Количество пропущенных шайб
     */
    private int goalsAgainst;

    /**
     * Количество забитых голов (шайб)
     */
    private int goalsScored;

    /**
     * Количество игр без забитых голов
     */
    private int gamesWithoutGoals;

    /**
     * Количество сухих игр (игр без пропущенных голов)
     */
    private int shutouts;

    /**
     * Серия без поражений или побед подряд
     */
    private int streak;

    /**
     * Процент выигранных игр
     */
    private double winPercentage;

    /**
     * Количество минут штрафа
     */
    private int penaltiesInMinutes;

    /**
     * Количество минут штрафа соперника
     */
    private int penaltiesInMinutesAgainst;

    /**
     * Общее количество заброшенных и пропущенных шайб
     */
    private int totalGoals;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private LocalDateTime createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private LocalDateTime updatedAt;

    /**
     * Метод для вычисления производных полей перед сохранением
     */
    @PrePersist
    @PreUpdate
    private void calculateDerivedFields() {
        winPercentage = (double) (wins + overtimeWins + shootoutWins) / gamesPlayed * 100;
        totalGoals = goalsFor + goalsAgainst;
    }

}
