package com.kalidratorma.yss.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

/**
 * Физические показатели на определенную дату
 */
@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Table(name = "physiology")
public class Physiology {

    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "physiology_seq")
    @SequenceGenerator(
            name = "physiology_seq",
            sequenceName = "physiology_seq",
            allocationSize = 1
    )
    @Column(name = "id", updatable = false)
    private Long id;

    /**
     * Дата
     */
    @Column(name = "date", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date date;

    /**
     * Рост
     */
    private int height;

    /**
     * Вес
     */
    private int weight;

    /**
     * Хват
     */
    @Enumerated(EnumType.STRING)
    private GripType grip;
}
