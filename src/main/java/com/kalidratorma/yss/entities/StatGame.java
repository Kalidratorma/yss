package com.kalidratorma.yss.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kalidratorma.yss.file.ContentFile;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Статистика игры
 */
@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Table(name = "stat_game"
        , uniqueConstraints = { @UniqueConstraint(columnNames = {"game_id"})}
        , indexes = {@Index(name = "IDX_STAT_GAME_GAME_ID", columnList = "game_id")}
)
public class StatGame {

    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stat_game_seq")
    @SequenceGenerator(
            name = "stat_game_seq",
            sequenceName = "stat_game_seq",
            allocationSize = 1
    )
    @Column(name = "id", updatable = false)
    private Long id;

    /**
     * Игра
     */
    @ManyToOne
    private Game game;

    /**
     * Тип окончания игры (основное время, овертайм, буллиты)
     */
    @Enumerated(EnumType.STRING)
    private GameEndEnum gameEndType;

    /**
     * Общее количество набранных очков
     */
    private int points;

    /**
     * Количество заброшенных шайб
     */
    private int goals;

    /**
     * Количество пропущенных шайб
     */
    private int goalsAgainst;

    /**
     * Общее количество заброшенных и пропущенных шайб
     */
    private int totalGoals;

    /**
     * Количество минут штрафа
     */
    private int penaltiesInMinutes;

    /**
     * Количество минут штрафа соперника
     */
    private int penaltiesInMinutesAgainst;

    /**
     * Список файлов с протоколом
     */
    @OneToMany
    private List<ContentFile> protocolFiles;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @JsonIgnore
    private LocalDateTime createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @JsonIgnore
    private LocalDateTime updatedAt;

    /**
     * Метод для вычисления производных полей перед сохранением
     */
    @PrePersist
    @PreUpdate
    private void calculateDerivedFields() {
        totalGoals = goals + goalsAgainst;
    }

}
