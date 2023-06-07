package com.kalidratorma.yss.entities;

import com.kalidratorma.yss.security.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Table(name = "coach")
public class Coach {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="coach_seq")
    @SequenceGenerator(
            name="coach_seq",
            sequenceName="coach_seq",
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
     * Телефон
     */
    private String phoneNumber;

    /**
     * Пол
     */
    @Enumerated(EnumType.STRING)
    private Sex sex;

    /**
     * Образование
     */
    private String education;

    /**
     * Договор
     */
    @OneToOne
    private Contract contract;

    /**
     * Тип тренера
     */
    private String coachType;

    /**
     * Пользователь
     */
    @OneToOne
    private User user;
}
