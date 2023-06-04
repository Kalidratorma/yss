package com.kalidratorma.yss.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Table(name = "stat")
public class Stat {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="stat_seq")
    @SequenceGenerator(
            name="stat_seq",
            sequenceName="stat_seq",
            allocationSize=1
    )
    @Column(name = "id", updatable = false)
    private Long id;

    /**
     * Дата
     */
    private Date date;

    /**
     * Гол
     */
    private int goal;

    /**
     * Пас
     */
    private int assist;

    /**
     * Очки
     */
    private int points;

    /**
     * Штраф
     */
    private int penalty;
}
