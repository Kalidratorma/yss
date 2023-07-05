package com.kalidratorma.yss.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Команда клуба соперников
 */
@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "club_team",
        indexes = {@Index(name = "IDX_CLUB_TEAM_NAME", columnList = "name")})
public class ClubTeam {

    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "club_team_seq")
    @SequenceGenerator(
            name = "club_team_seq",
            sequenceName = "club_team_seq",
            allocationSize = 1
    )
    @Column(name = "id", updatable = false)
    private Long id;

    /**
     * Клуб
     */
    @ManyToOne
    private Club club;

    /**
     * Наименование команды
     */
    private String name;

    /**
     * Контакты
     */
    private String contacts;

    /**
     * Год команды
     */
    @OneToOne
    private TeamYear teamYear;
}
