package com.kalidratorma.yss.entities;

import com.fasterxml.jackson.annotation.JsonFilter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.List;

@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "player",
        indexes = {@Index(name = "IDX_PLAYER_NAME", columnList = "surname,name,patronymic")})
@JsonFilter("PlayerFilter")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="player_seq")
    @SequenceGenerator(
            name="player_seq",
            sequenceName="player_seq",
            allocationSize=1
    )
    @Column(name = "id", updatable = false)
    private Long id;

    /**
     * Фамилия
     */
    private String surname;

    /**
     * Имя
     */
    private String name;

    /**
     * Отчество
     */
    private String patronymic;

    /**
     * Дата рождения
     */
    private Date birthDate;

    /**
     * Ограничения
     */
    private String restrictions;

    /**
     * Родители
     */
    @ManyToMany
    private List<Parent> parents;

    /**
     * Пол
     */
    @Enumerated(EnumType.STRING)
    private Sex sex;


    /**
     * Игровой номер
     */
    private byte number;

    /**
     * Год команды
     */
    private short teamYear;

    /**
     * Фото
     */
    private String photo;

    /**
     * Амплуа/позиция
     */
    @OneToOne
    private Position position;

    /**
     * Договора
     */
    @OneToMany
    private List<Contract> contracts;

    /**
     * Физиология
     */
    @OneToMany
    private List<Physiology> physiologyList;

    /**
     * Статистика
     */
    @OneToMany
    private List<Stat> stats;
}
