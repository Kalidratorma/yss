package com.kalidratorma.yss.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

/**
 * Статистика спортсмена
 */
@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Table(name = "stat")
public class Stat {

    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stat_seq")
    @SequenceGenerator(
            name = "stat_seq",
            sequenceName = "stat_seq",
            allocationSize = 1
    )
    @Column(name = "id", updatable = false)
    private Long id;

    /**
     * Дата
     */
    @Temporal(TemporalType.DATE)
    private LocalDate date;

    /**
     * Голы
     */
    private int goal;

    /**
     * Пасы
     */
    private int assist;

    /**
     * Очки
     */
    private int points;

    /**
     * Штрафы
     */
    private int penalty;
}
