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
 * Тренировка
 */
@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "training")
public class Training {

    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "training_seq")
    @SequenceGenerator(
            name = "training_seq",
            sequenceName = "training_seq",
            allocationSize = 1
    )
    @Column(name = "id", updatable = false)
    private Long id;

    /**
     * Формат тренировки
     */
    @ManyToOne
    private TrainingFormat trainingFormat;

    /**
     * Команда
     */
    @ManyToOne
    private Team team;

    /**
     * Дата тренировки
     */
    @Temporal(TemporalType.DATE)
    private LocalDate date;

    /**
     * Время тренировки (начало)
     */
    @Temporal(TemporalType.TIME)
    private LocalTime time;

    /**
     * Длительность тренировки в минутах
     */
    private int duration;

    /**
     * Главный тренер
     */
    @ManyToOne
    private Coach mainCoach;

    /**
     * Помощники тренера
     */
    @ManyToMany
    @JoinTable(name = "training_coach",
            joinColumns = @JoinColumn(name = "training_id"),
            inverseJoinColumns = @JoinColumn(name = "coach_id"))
    private Set<Coach> coaches;
}
