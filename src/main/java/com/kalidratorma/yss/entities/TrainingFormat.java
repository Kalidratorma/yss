package com.kalidratorma.yss.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;


/**
 * Формат тренировки
 */
@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "training_format")
public class TrainingFormat {

    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "training_format_seq")
    @SequenceGenerator(
            name = "training_format_seq",
            sequenceName = "training_format_seq",
            allocationSize = 1
    )
    @Column(name = "id", updatable = false)
    private Long id;

    /**
     * Наименование
     */
    private String name;

    /**
     * Краткое наименование
     */
    private String shortName;

    /**
     * Описание
     */
    private String description;

    /**
     * Тренера
     */
    @ManyToMany
    @JoinTable(name = "traning_format_coach",
            joinColumns = @JoinColumn(name = "traning_format_id"),
            inverseJoinColumns = @JoinColumn(name = "coach_id"))
    private Set<Coach> coaches;
}
