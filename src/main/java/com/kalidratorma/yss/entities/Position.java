package com.kalidratorma.yss.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Амплуа/Позиция
 */
@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "position")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false)
    private Long id;

    /**
     * Сокращенное название
     */
    @Column(nullable = false, unique = true)
    private String shortName;

    /**
     * Расширенное название
     */
    @Column(nullable = false, unique = true)
    private String name;

}
