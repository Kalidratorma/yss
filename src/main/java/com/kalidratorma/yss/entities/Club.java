package com.kalidratorma.yss.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Клуб соперника
 */
@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "club",
        indexes = {@Index(name = "IDX_CLUB_NAME", columnList = "name")})
public class Club {

    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "club_seq")
    @SequenceGenerator(
            name = "club_seq",
            sequenceName = "club_seq",
            allocationSize = 1
    )
    @Column(name = "id", updatable = false)
    private Long id;

    /**
     * Наименование клуба соперника
     */
    private String name;

    /**
     * Сайт
     */
    private String siteUrl;

    /**
     * Контакты
     */
    private String contacts;
}
