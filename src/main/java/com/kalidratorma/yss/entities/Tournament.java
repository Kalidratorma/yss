package com.kalidratorma.yss.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * Турнир
 */
@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Table(name = "tournament")
public class Tournament {

    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tournament_seq")
    @SequenceGenerator(
            name = "tournament_seq",
            sequenceName = "tournament_seq",
            allocationSize = 1
    )
    @Column(name = "id", updatable = false)
    private Long id;

    /**
     * Название турнира
     */
    private String name;

    /**
     * Контакты организаторов
     */
    private String phoneNumber;

    /**
     * Электронная почта
     */
    private String email;

    /**
     * ФИО организатора
     */
    private String fullName;

    /**
     * Ссылка на сайт
     */
    private String siteUrl;

    /**
     * Ссылка на Youtube
     */
    private String youtubeUrl;

    /**
     * Тип турнира
     */
    @Enumerated(EnumType.STRING)
    private TournamentEnum type;

    /**
     * Примечание
     */
    private String note;
}
