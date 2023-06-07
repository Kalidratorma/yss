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
@Table(name = "physiology")
public class Physiology {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="physiology_seq")
    @SequenceGenerator(
            name="physiology_seq",
            sequenceName="physiology_seq",
            allocationSize=1
    )
    @Column(name = "id", updatable = false)
    private Long id;
    
    /**
     * Дата
     */
    private Date date;

    /**
     * Рост (в миллиметрах)
     */
    private int height;

    /**
     * Вес (в граммах)
     */
    private int weight;

    /**
     * Хват
     */
    @Enumerated(EnumType.STRING)
    private GripType grip;
}
