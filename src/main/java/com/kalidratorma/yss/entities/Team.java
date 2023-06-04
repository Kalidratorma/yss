package com.kalidratorma.yss.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Table(name = "team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false)
    private Long id;

    /**
     * Название команды
     */
    private String name;

    /**
     * Год команды
     */
    private short teamYear;

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
