package com.kalidratorma.yss.entities;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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
     * Родители
     */
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "players")
    @JsonManagedReference
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
     * Договор
     */
    @OneToOne
    private Contract contract;

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
