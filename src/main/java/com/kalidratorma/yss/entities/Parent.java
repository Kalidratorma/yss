package com.kalidratorma.yss.entities;

import com.kalidratorma.yss.security.user.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Родитель (клиент)
 */
@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Table(name = "parent")
public class Parent {

    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parent_seq")
    @SequenceGenerator(
            name = "parent_seq",
            sequenceName = "parent_seq",
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
     * Пользователь
     */
    @OneToOne
    private User user;

    /**
     * Договора
     */
    @ManyToMany
    private Set<Contract> contracts;

    /**
     * Игроки
     */
    @ManyToMany
    @JoinTable(name = "parent_player",
            joinColumns = @JoinColumn(name = "parent_id"),
            inverseJoinColumns = @JoinColumn(name = "player_id"))
    private Set<Player> players = new HashSet<>();
}
