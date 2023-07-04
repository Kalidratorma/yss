package com.kalidratorma.yss.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * Состав
 */
@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Table(name = "composition")
public class Composition {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "composition_seq")
    @SequenceGenerator(
            name = "composition_seq",
            sequenceName = "composition_seq",
            allocationSize = 1
    )
    @Column(name = "id", updatable = false)
    private Long id;

    /**
     * Номер состава
     */
    private byte compositionNumber;

    /**
     * Звенья
     */
    @OneToMany
    private List<Unit> units;

    /**
     * Команда
     */
    @ManyToOne
    private Team team;
}
