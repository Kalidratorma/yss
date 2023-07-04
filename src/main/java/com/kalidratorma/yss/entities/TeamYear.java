package com.kalidratorma.yss.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * Год команды
 */
@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Table(name = "team_year")
public class TeamYear {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "team_year_seq")
    @SequenceGenerator(
            name = "team_year_seq",
            sequenceName = "team_year_seq",
            allocationSize = 1
    )
    @Column(name = "id", updatable = false)
    private Long id;

    /**
     * Год команды
     */
    private short year;

    /**
     * Название команды
     */
    private String teamName;

    /**
     * Примечание
     */
    private String note;
}
