package com.kalidratorma.yss.entities;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.kalidratorma.yss.file.ContentFile;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * Задание тренера
 */
@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Table(name = "task")
@JsonFilter("TaskFilter")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_seq")
    @SequenceGenerator(
            name = "task_seq",
            sequenceName = "task_seq",
            allocationSize = 1
    )
    @Column(name = "id", updatable = false)
    private Long id;

    /**
     * Наименование задания
     */
    @Column(nullable = false)
    private String name;

    /**
     * Текст задания
     */
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    /**
     * Длительность задания в минутах
     */
    @Column
    private int minutes;

    /**
     * Количество повторений
     */
    @Column
    private int qty;

    /**
     * Ссылки на видео
     */
    @OneToMany
    private List<ContentFile> videoLinks;

    /**
     * Ссылки на фото
     */
    @OneToMany
    private List<ContentFile> photoLinks;

    /**
     * Расписание
     */
    @OneToMany
    private List<JustDate> schedule;

    /**
     * Игроки
     */
    @OneToMany
    private List<Player> players;

    /**
     * Тренер
     */
    @OneToOne
    private Coach coach;
}
