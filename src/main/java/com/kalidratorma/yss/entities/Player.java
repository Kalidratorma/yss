package com.kalidratorma.yss.entities;

import com.fasterxml.jackson.annotation.JsonFilter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

/**
 * Спортсмен
 */
@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "player",
        indexes = {@Index(name = "IDX_PLAYER_NAME", columnList = "surname,name,patronymic")})
@JsonFilter("PlayerFilter")
public class Player {

    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "player_seq")
    @SequenceGenerator(
            name = "player_seq",
            sequenceName = "player_seq",
            allocationSize = 1
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
     * Пол
     */
    @Enumerated(EnumType.STRING)
    private Sex sex;

    /**
     * Дата рождения
     */
    @Temporal(TemporalType.DATE)
    private LocalDate birthDate;

    /**
     * Почта
     */
    private String email;

    /**
     * Телефон
     */
    private String phoneNumber;

    /**
     * Фото
     */
    private String photo;

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
     * Игровой номер
     */
    private byte number;

    /**
     * Год команды
     */
    @OneToOne
    private TeamYear teamYear;

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
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Physiology> physiologyList;

    /**
     * Статистика
     */
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Stat> stats;
}
