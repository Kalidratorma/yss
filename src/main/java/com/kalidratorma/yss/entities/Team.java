package com.kalidratorma.yss.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * Команда
 */
@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Table(name = "team")
public class Team {

    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "team_seq")
    @SequenceGenerator(
            name = "team_seq",
            sequenceName = "team_seq",
            allocationSize = 1
    )
    @Column(name = "id", updatable = false)
    private Long id;

    /**
     * Название команды
     */
    private String name;

    /**
     * Год команды
     */
    @ManyToOne
    private TeamYear teamYear;

    /**
     * Формат игры
     */
    @OneToOne(fetch = FetchType.LAZY)
    private GameFormat gameFormat;

    /**
     * Главный тренер
     */
    @OneToOne(fetch = FetchType.LAZY)
    private Coach headCoach;

    /**
     * Помощник главного тренера
     */
    @OneToOne(fetch = FetchType.LAZY)
    private Coach assistantCoach;

    /**
     * Состав
     */
    @OneToMany
    private List<Composition> unitList;
}
